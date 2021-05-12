package flowcontrol.production.service;

import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.Article;
import flowcontrol.production.model.general.Farmer;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.repository.LineRepository;
import flowcontrol.production.repository.TicketRepository;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import org.aspectj.lang.annotation.Before;
import org.h2.tools.Server;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.BeforeTestClass;


import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;


class MockData {

    public Line getLine() {
        Line line = new Line();
        line.setId(1l);
        line.setName("Line one");
        line.setDescription("Line one");
        return line;
    }

    public Article getArticle() {
        Article article = new Article();
        article.setName("Test article");
        return article;
    }

    public Farmer getFarmer() {
        Farmer farmer = new Farmer();
        farmer.setId(1l);
        farmer.setName("Test farmer");
        return farmer;
    }

    public PalletLabel getPalletLabel() {
        PalletLabel palletLabel = new PalletLabel();
        palletLabel.setId(1l);
        palletLabel.setPalletLabelFarmerId(1l);
        palletLabel.setArticle(this.getArticle());
        palletLabel.setArticleAmount(180);
        palletLabel.setFarmer(this.getFarmer());
        palletLabel.setCropDate("2020-05-02");
        palletLabel.setGeneralId(1l);
        palletLabel.setNote("Test note");
        return palletLabel;
    }
}

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    @Autowired
    MockData mockData;


    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private LineRepository lineRepository;
    @MockBean
    private PalletLabelRepository palletLabelRepository;

    private TicketService ticketService;



    @BeforeTestClass
    static void initTests() throws SQLException {
        Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082")
                .start();
    }

    @BeforeEach
    void setUp() {
        ticketService = new TicketService(ticketRepository, lineRepository, palletLabelRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void itShouldCloseTicket() {
    }

    @Test
    void itShouldCreateNewTicket() {


        when(palletLabelRepository.findById(1l,1l)).then((Answer<Optional<PalletLabel>>) invocation -> Optional.of(mockData.getPalletLabel()));
        when(lineRepository.findById(anyLong())).then((Answer<Optional<Line>>) invocation -> Optional.of(mockData.getLine()));

        //given
        BasicMetaData metaData = BasicMetaData.builder()
                .farmerId(1l)
                .palletLabelId(1l)
                .build();

        //when
        Ticket ticket = ticketService.create(metaData, 1l).get();

        //then
        verify(ticketRepository).save(ticket);
    }
}