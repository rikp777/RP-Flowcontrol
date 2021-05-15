package flowcontrol.production.service;

import flowcontrol.production.exception.TicketException;
import flowcontrol.production.model.entity.Line;
import flowcontrol.production.model.entity.Ticket;
import flowcontrol.production.model.general.Article;
import flowcontrol.production.model.general.Farmer;
import flowcontrol.production.model.general.PalletLabel;
import flowcontrol.production.model.meta.BasicMetaData;
import flowcontrol.production.repository.LineRepository;
import flowcontrol.production.repository.TicketRepository;
import flowcontrol.production.repository.impl.PalletLabelRepository;
import org.h2.tools.Server;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    public Ticket getTicket(){
        Ticket ticket = new Ticket();
        ticket.setId(1l);
        ticket.setFarmerId(getFarmer().getId());
        ticket.setPalletLabelId(getPalletLabel().getId());
        ticket.setStartAt(LocalDateTime.now());
        return ticket;
    }
}

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {
    MockData mockData;
    BasicMetaData metaData;

    @Mock
    private TicketRepository ticketRepository;
    @Mock
    private LineRepository lineRepository;
    @Mock
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
        mockData = new MockData();
        metaData = BasicMetaData.builder()
                .farmerId(1l)
                .palletLabelId(1l)
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void itShouldCloseTicket() {
        when(
                ticketRepository.getTicketsByFarmerIdAndPalletLabelIdAndId(anyLong(), anyLong(), anyLong())
        ).thenReturn(
                Optional.of(mockData.getTicket())
        );

        Ticket closedTicket = ticketService.close(metaData, mockData.getTicket().getId()).get();

        verify(ticketRepository).save(closedTicket);

        assertThat(closedTicket.getEndAt()).isNotNull();


    }

    @Test
    void itShouldCreateNewTicket() throws Exception {
        //given

        when(
                palletLabelRepository.findById(metaData.getFarmerId(),metaData.getPalletLabelId())
        ).thenReturn(
                Optional.of(mockData.getPalletLabel())
        );

        when(
                lineRepository.findById(anyLong())
        ).thenReturn(
                Optional.of(mockData.getLine())
        );


        // First ticket for palletlabel
        //when
        Ticket firstTicket = ticketService.create(metaData, 1l).get();
        //then
        verify(ticketRepository).save(firstTicket);


        // Second ticket with rest amount
        //when
        Ticket ticketToReturnWithRest = mockData.getTicket();
        ticketToReturnWithRest.setArticleAmountUsed(160);
        when(
                ticketRepository.getTicketByFarmerIdAndPalletLabelId(anyLong(), anyLong())
        ).then((Answer<List<Ticket>>) invocation -> Arrays.asList(ticketToReturnWithRest));
        Ticket secondTicket = ticketService.create(metaData, 1l).get();
        //then
        verify(ticketRepository).save(secondTicket);

        // third ticket with no rest amount
        Ticket ticketToReturnNoRest = mockData.getTicket();
        ticketToReturnNoRest.setArticleAmountUsed(180);
        when(
                ticketRepository.getTicketByFarmerIdAndPalletLabelId(anyLong(), anyLong())
        ).then((Answer<List<Ticket>>) invocation -> Arrays.asList(ticketToReturnNoRest));
        //then

        Throwable thrown = catchThrowable(() -> {
            ticketService.create(metaData, 1l);
        });

        assertThat(thrown)
                .isInstanceOf(TicketException.class);
    }
}