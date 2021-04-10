package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.*;
import flowcontrol.article.repository.ArticleRepository;
import flowcontrol.article.repository.CaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;

@Configuration
@Slf4j
public class ArticleSeeder {
    @Autowired
    private final ArticleRepository articleRepo;

    public ArticleSeeder(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }



    public void run(UtilSeeder util) {
        if(!articleRepo.findById(1L).isPresent()) {
            Color brown = util.findColorInSet("Brown");
            Color white = util.findColorInSet("White");
            Color grey = util.findColorInSet("Grey");
            Color pink = util.findColorInSet("Pink");
            Color yellow = util.findColorInSet("Yellow");

            PalletType dpa = util.findPalletTypeInSet("DPA");
            PalletType plastic = util.findPalletTypeInSet("Plastic");
            PalletType euro = util.findPalletTypeInSet("Euro");

            Cask emballageMulti = util.findCaskInSet("Emballage multi");


            // region Wit
            Article A1 = new Article(); //1
            A1.setExcelCode("WI0000NL");
            A1.setInsetLimit(1);
            A1.setInsetGram(2500);
            A1.setPalletLimit(170);
            A1.setOrigin("NL");
            A1.setPalletType(dpa);
            A1.setCask(util.findCaskInSet("Emballage multi"));
            A1.setGroup(util.findGroupInSet("Wit"));
            A1.setSortType(util.findSortTypeInSet("Industrie (3.3.100)"));
            articleRepo.save(A1);

            Article A2 = new Article(); //2
            A2.setExcelCode("WI0000NL");
            A2.setInsetLimit(1);
            A2.setPalletLimit(170);
            A2.setOrigin("NL");
            A2.setPalletType(plastic);
            A2.setCask(util.findCaskInSet("Emballage multi"));
            A2.setInset(util.findInsetInSet("ID0052ST"));
            A2.setGroup(util.findGroupInSet("Wit"));
            A2.setSortType(util.findSortTypeInSet("Industrie"));
            articleRepo.save(A2);

            Article W3 = new Article(); //32
            W3.setExcelCode("WF0000NL");
            W3.setInsetLimit(6);
            W3.setInsetGram(250);
            W3.setPalletLimit(170);
            W3.setOrigin("NL");
            W3.setPalletType(dpa);
            W3.setCask(util.findCaskInSet("Emballage multi"));
            W3.setInset(util.findInsetInSet("ID0052ST"));
            W3.setGroup(util.findGroupInSet("Wit"));
            W3.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W3);

            Article W4 = new Article(); //33
            W4.setExcelCode("WF0000NL");
            W4.setInsetLimit(6);
            W4.setInsetGram(250);
            W4.setPalletLimit(170);
            W4.setOrigin("NL");
            W4.setPalletType(dpa);
            W4.setCask(util.findCaskInSet("Emballage multi"));
            W4.setInset(util.findInsetInSet("ID0050BL"));
            W4.setGroup(util.findGroupInSet("Wit"));
            W4.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W4);

            Article W5 = new Article(); //34
            W5.setExcelCode("WF0000NL");
            W5.setInsetLimit(4);
            W5.setInsetGram(400);
            W5.setPalletLimit(170);
            W5.setOrigin("NL");
            W5.setPalletType(dpa);
            W5.setCask(util.findCaskInSet("Emballage multi"));
            W5.setInset(util.findInsetInSet("ID0190TR"));
            W5.setGroup(util.findGroupInSet("Wit"));
            W5.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W5);

            Article W6 = new Article(); //35
            W6.setExcelCode("WF0000NL");
            W6.setInsetLimit(4);
            W6.setInsetGram(400);
            W6.setPalletLimit(170);
            W6.setOrigin("NL");
            W6.setPalletType(dpa);
            W6.setCask(util.findCaskInSet("Emballage multi"));
            W6.setInset(util.findInsetInSet("ID0190BL"));
            W6.setGroup(util.findGroupInSet("Wit"));
            W6.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W6);

            Article W7 = new Article(); //36
            W7.setExcelCode("WF0000NL");
            W7.setInsetLimit(2);
            W7.setInsetGram(1000);
            W7.setPalletLimit(170);
            W7.setOrigin("NL");
            W7.setPalletType(dpa);
            W7.setCask(util.findCaskInSet("Emballage multi"));
            W7.setInset(util.findInsetInSet("ID0205BL"));
            W7.setGroup(util.findGroupInSet("Wit"));
            W7.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W7);

            Article W8 = new Article(); //37
            W8.setExcelCode("WF0000NL");
            W8.setInsetLimit(3);
            W8.setInsetGram(600);
            W8.setPalletLimit(170);
            W8.setOrigin("NL");
            W8.setPalletType(dpa);
            W8.setCask(util.findCaskInSet("Emballage multi"));
            W8.setInset(util.findInsetInSet("ID0220BL"));
            W8.setGroup(util.findGroupInSet("Wit"));
            W8.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W8);

            Article W9 = new Article(); //38
            W9.setExcelCode("WF0000NL");
            W9.setInsetLimit(1);
            W9.setPalletLimit(170);
            W9.setOrigin("NL");
            W9.setPalletType(dpa);
            W9.setCask(util.findCaskInSet("Emballage multi"));
            W9.setGroup(util.findGroupInSet("Wit"));
            W9.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W9);

            Article W10 = new Article(); //38
            W10.setExcelCode("WF0000NL");
            W10.setInsetLimit(1);
            W10.setInsetGram(2500);
            W10.setPalletLimit(170);
            W10.setOrigin("NL");
            W10.setPalletType(dpa);
            W10.setCask(util.findCaskInSet("Emballage multi"));
            W10.setGroup(util.findGroupInSet("Wit"));
            W10.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W10);

            Article W11 = new Article(); //39
            W11.setExcelCode("WF0000NL");
            W11.setInsetLimit(1);
            W11.setInsetGram(2500);
            W11.setPalletLimit(170);
            W11.setOrigin("NL");
            W11.setPalletType(dpa);
            W11.setCask(util.findCaskInSet("Emballage multi"));
            W11.setGroup(util.findGroupInSet("Wit"));
            W11.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W11);

            Article W12 = new Article(); //40
            W12.setExcelCode("WF0000NL");
            W12.setInsetLimit(1);
            W12.setInsetGram(3000);
            W12.setPalletLimit(170);
            W12.setOrigin("NL");
            W12.setPalletType(dpa);
            W12.setCask(util.findCaskInSet("Emballage multi"));
            W12.setGroup(util.findGroupInSet("Wit"));
            W12.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W12);

            Article W13 = new Article(); //41
            W13.setExcelCode("WM0000NL");
            W13.setInsetLimit(4);
            W13.setInsetGram(150);
            W13.setPalletLimit(170);
            W13.setOrigin("NL");
            W13.setPalletType(dpa);
            W13.setCask(util.findCaskInSet("Emballage multi"));
            W13.setInset(util.findInsetInSet("ID0705ST"));
            W13.setGroup(util.findGroupInSet("Wit"));
            W13.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W13);

            Article W14 = new Article(); //42
            W14.setExcelCode("WM0000NL");
            W14.setInsetLimit(6);
            W14.setInsetGram(250);
            W14.setPalletLimit(170);
            W14.setOrigin("NL");
            W14.setPalletType(dpa);
            W14.setCask(util.findCaskInSet("Emballage multi"));
            W14.setInset(util.findInsetInSet("ID0093TR"));
            W14.setGroup(util.findGroupInSet("Wit"));
            W14.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W14);

            Article W15 = new Article(); //43
            W15.setExcelCode("WM0000NL");
            W15.setInsetLimit(6);
            W15.setInsetGram(250);
            W15.setPalletLimit(170);
            W15.setOrigin("NL");
            W15.setPalletType(dpa);
            W15.setCask(util.findCaskInSet("Emballage multi"));
            W15.setInset(util.findInsetInSet("ID0220GR"));
            W15.setGroup(util.findGroupInSet("Wit"));
            W15.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W15);

            Article W16 = new Article(); //44
            W16.setExcelCode("WM0000NL");
            W16.setInsetLimit(4);
            W16.setInsetGram(400);
            W16.setPalletLimit(170);
            W16.setOrigin("NL");
            W16.setPalletType(dpa);
            W16.setCask(util.findCaskInSet("Emballage multi"));
            W16.setInset(util.findInsetInSet("ID0190TR"));
            W16.setGroup(util.findGroupInSet("Wit"));
            W16.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W16);

            Article W17 = new Article(); //45
            W17.setExcelCode("WM0000NL");
            W17.setInsetLimit(4);
            W17.setInsetGram(400);
            W17.setPalletLimit(170);
            W17.setOrigin("NL");
            W17.setPalletType(dpa);
            W17.setCask(util.findCaskInSet("Emballage multi"));
            W17.setInset(util.findInsetInSet("ID0190BL"));
            W17.setGroup(util.findGroupInSet("Wit"));
            W17.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W17);

            Article W18 = new Article(); //46
            W18.setExcelCode("WM0000NL");
            W18.setInsetLimit(4);
            W18.setInsetGram(400);
            W18.setPalletLimit(170);
            W18.setOrigin("NL");
            W18.setPalletType(dpa);
            W18.setCask(util.findCaskInSet("Emballage multi"));
            W18.setInset(util.findInsetInSet("ID0205BL"));
            W18.setGroup(util.findGroupInSet("Wit"));
            W18.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W18);

            Article W19 = new Article(); //47
            W19.setExcelCode("WM0000NL");
            W19.setInsetLimit(2);
            W19.setInsetGram(1000);
            W19.setPalletLimit(170);
            W19.setOrigin("NL");
            W19.setPalletType(dpa);
            W19.setCask(util.findCaskInSet("Emballage multi"));
            W19.setInset(util.findInsetInSet("ID0243BL"));
            W19.setGroup(util.findGroupInSet("Wit"));
            W19.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W19);

            Article W20 = new Article(); //48
            W20.setExcelCode("WM0000NL");
            W20.setInsetLimit(3);
            W20.setInsetGram(600);
            W20.setPalletLimit(170);
            W20.setOrigin("NL");
            W20.setPalletType(dpa);
            W20.setCask(util.findCaskInSet("Emballage multi"));
            W20.setInset(util.findInsetInSet("ID0220BL"));
            W20.setGroup(util.findGroupInSet("Wit"));
            W20.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W20);

            Article W21 = new Article(); //49
            W21.setExcelCode("WM0000NL");
            W21.setInsetLimit(4);
            W21.setInsetGram(500);
            W21.setPalletLimit(170);
            W21.setOrigin("NL");
            W21.setPalletType(dpa);
            W21.setCask(util.findCaskInSet("Emballage multi"));
            W21.setInset(util.findInsetInSet("ID0190TR"));
            W21.setGroup(util.findGroupInSet("Wit"));
            W21.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W21);

            Article W22 = new Article(); //50
            W22.setExcelCode("WM0000NL");
            W22.setInsetLimit(4);
            W22.setInsetGram(500);
            W22.setPalletLimit(170);
            W22.setOrigin("NL");
            W22.setPalletType(dpa);
            W22.setCask(util.findCaskInSet("Emballage multi"));
            W22.setInset(util.findInsetInSet("ID0190BL"));
            W22.setGroup(util.findGroupInSet("Wit"));
            W22.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W22);

            Article W23 = new Article(); //51
            W23.setExcelCode("WM0000NL");
            W23.setInsetLimit(4);
            W23.setInsetGram(100);
            W23.setPalletLimit(170);
            W23.setOrigin("NL");
            W23.setPalletType(dpa);
            W23.setInset(util.findInsetInSet("ID0710TR"));
            W23.setGroup(util.findGroupInSet("Wit"));
            W23.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W23);

            Article W24 = new Article(); //52
            W24.setExcelCode("WM0000NL");
            W24.setInsetLimit(1);
            W24.setPalletLimit(170);
            W24.setOrigin("NL");
            W24.setPalletType(dpa);
            W24.setCask(util.findCaskInSet("Emballage multi"));
            W24.setGroup(util.findGroupInSet("Wit"));
            W24.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W24);

            Article W25 = new Article(); //53
            W25.setExcelCode("WM0000NL");
            W25.setInsetLimit(1);
            W25.setInsetGram(2500);
            W25.setPalletLimit(170);
            W25.setOrigin("NL");
            W25.setPalletType(dpa);
            W25.setCask(util.findCaskInSet("Emballage multi"));
            W25.setGroup(util.findGroupInSet("Wit"));
            W25.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W25);

            Article W26 = new Article(); //54
            W26.setExcelCode("WM0000NL");
            W26.setInsetLimit(1);
            W26.setInsetGram(3000);
            W26.setPalletLimit(170);
            W26.setOrigin("NL");
            W26.setPalletType(dpa);
            W26.setCask(util.findCaskInSet("Emballage multi"));
            W26.setGroup(util.findGroupInSet("Wit"));
            W26.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W26);

            Article W27 = new Article(); //55
            W27.setExcelCode("WM0000PL");
            W27.setInsetLimit(8);
            W27.setInsetGram(400);
            W27.setPalletLimit(170);
            W27.setOrigin("PL");
            W27.setPalletType(dpa);
            W27.setCask(util.findCaskInSet("Emballage multi"));
            W27.setGroup(util.findGroupInSet("Wit"));
            W27.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W27);

            Article W28 = new Article(); //56
            W28.setExcelCode("WR0000NL");
            W28.setInsetLimit(6);
            W28.setInsetGram(250);
            W28.setPalletLimit(170);
            W28.setOrigin("NL");
            W28.setPalletType(dpa);
            W28.setCask(util.findCaskInSet("Emballage multi"));
            W28.setInset(util.findInsetInSet("ID0050BL"));
            W28.setGroup(util.findGroupInSet("Wit"));
            W28.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W28);


            Article W29 = new Article(); //57
            W29.setExcelCode("WR0000NL");
            W29.setInsetLimit(4);
            W29.setInsetGram(400);
            W29.setPalletLimit(170);
            W29.setOrigin("NL");
            W29.setPalletType(dpa);
            W29.setCask(util.findCaskInSet("Emballage multi"));
            W29.setInset(util.findInsetInSet("ID0190BL"));
            W29.setGroup(util.findGroupInSet("Wit"));
            W29.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W29);

            Article W30 = new Article(); //58
            W30.setExcelCode("WR0000NL");
            W30.setInsetLimit(2);
            W30.setInsetGram(1000);
            W30.setPalletLimit(170);
            W30.setOrigin("NL");
            W30.setPalletType(dpa);
            W30.setCask(util.findCaskInSet("Emballage multi"));
            W30.setInset(util.findInsetInSet("ID0243BL"));
            W30.setGroup(util.findGroupInSet("Wit"));
            W30.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W30);

            Article W31 = new Article(); //59
            W31.setExcelCode("WR0000NL");
            W31.setInsetLimit(1);
            W31.setPalletLimit(170);
            W31.setOrigin("NL");
            W31.setPalletType(dpa);
            W31.setCask(util.findCaskInSet("Emballage multi"));
            W31.setGroup(util.findGroupInSet("Wit"));
            W31.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W31);

            Article W32 = new Article(); //60
            W32.setExcelCode("WR0000NL");
            W32.setInsetLimit(1);
            W32.setInsetGram(2000);
            W32.setPalletLimit(170);
            W32.setOrigin("NL");
            W32.setPalletType(dpa);
            W32.setCask(util.findCaskInSet("Emballage multi"));
            W32.setGroup(util.findGroupInSet("Wit"));
            W32.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W32);

            Article W33 = new Article(); //61
            W33.setExcelCode("WR0000NLBL");
            W33.setInsetLimit(1);
            W33.setInsetGram(2000);
            W33.setPalletLimit(170);
            W33.setOrigin("NL/BL");
            W33.setPalletType(dpa);
            W33.setCask(util.findCaskInSet("Emballage multi"));
            W33.setGroup(util.findGroupInSet("Wit"));
            W33.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W33);

            Article W34 = new Article(); //62
            W34.setExcelCode("WR0000NLGR");
            W34.setInsetLimit(1);
            W34.setInsetGram(2000);
            W34.setPalletLimit(170);
            W34.setOrigin("NL/GR");
            W34.setPalletType(dpa);
            W34.setCask(util.findCaskInSet("Emballage multi"));
            W34.setGroup(util.findGroupInSet("Wit"));
            W34.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W34);

            Article W35 = new Article(); //63
            W35.setExcelCode("WEF0000NL");
            W35.setInsetLimit(2);
            W35.setInsetGram(1000);
            W35.setPalletLimit(170);
            W35.setOrigin("NL");
            W35.setPalletType(dpa);
            W35.setCask(util.findCaskInSet("Emballage multi"));
            W35.setInset(util.findInsetInSet("ID0243BL"));
            W35.setGroup(util.findGroupInSet("Wit"));
            W35.setSortType(util.findSortTypeInSet("Extra fijn"));
            articleRepo.save(W35);

            Article W36 = new Article(); //64
            W36.setExcelCode("WEF0000NL");
            W36.setInsetLimit(1);
            W36.setPalletLimit(170);
            W36.setOrigin("NL");
            W36.setPalletType(dpa);
            W36.setCask(util.findCaskInSet("Emballage multi"));
            W36.setGroup(util.findGroupInSet("Wit"));
            W36.setSortType(util.findSortTypeInSet("Extra fijn"));
            articleRepo.save(W36);

            Article W37 = new Article(); //65
            W37.setExcelCode("WEF0000NL");
            W37.setInsetLimit(1);
            W37.setInsetGram(2500);
            W37.setPalletLimit(170);
            W37.setOrigin("NL");
            W37.setPalletType(dpa);
            W37.setCask(util.findCaskInSet("Emballage multi"));
            W37.setGroup(util.findGroupInSet("Wit"));
            W37.setSortType(util.findSortTypeInSet("Extra fijn"));
            articleRepo.save(W37);

            Article W38 = new Article(); //66
            W38.setExcelCode("WEF0000NL");
            W38.setInsetLimit(1);
            W38.setInsetGram(3000);
            W38.setPalletLimit(170);
            W38.setOrigin("NL");
            W38.setPalletType(dpa);
            W38.setCask(util.findCaskInSet("Emballage multi"));
            W38.setGroup(util.findGroupInSet("Wit"));
            W38.setSortType(util.findSortTypeInSet("Extra fijn"));
            articleRepo.save(W38);

            Article W39 = new Article(); //71
            W39.setExcelCode("WM0000NL");
            W39.setInsetLimit(4);
            W39.setInsetGram(250);
            W39.setPalletLimit(170);
            W39.setOrigin("NL");
            W39.setPalletType(dpa);
            W39.setCask(util.findCaskInSet("Emballage multi"));
            W39.setInset(util.findInsetInSet("ID0710TR"));
            W39.setGroup(util.findGroupInSet("Wit"));
            W39.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W39);

            Article W40 = new Article(); //73
            W40.setExcelCode("WM0000NL");
            W40.setInsetLimit(4);
            W40.setInsetGram(400);
            W40.setPalletLimit(170);
            W40.setOrigin("NL");
            W40.setPalletType(dpa);
            W40.setCask(util.findCaskInSet("Emballage multi"));
            W40.setInset(util.findInsetInSet("ID1001BL"));
            W40.setGroup(util.findGroupInSet("Wit"));
            W40.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W40);

            Article W41 = new Article(); //75
            W41.setExcelCode("WM0000NL");
            W41.setInsetLimit(1);
            W41.setInsetGram(2500);
            W41.setPalletLimit(170);
            W41.setOrigin("NL");
            W41.setPalletType(dpa);
            W41.setCask(util.findCaskInSet("Emballage doos CHEF"));
            W41.setInset(util.findInsetInSet("ID2001BR"));
            W41.setGroup(util.findGroupInSet("Wit"));
            W41.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W41);

            Article W42 = new Article(); //78
            W42.setExcelCode("WF0000NL");
            W42.setInsetLimit(6);
            W42.setInsetGram(250);
            W42.setPalletLimit(170);
            W42.setOrigin("NL");
            W42.setPalletType(dpa);
            W42.setCask(util.findCaskInSet("Emballage multi"));
            W42.setInset(util.findInsetInSet("ID0093TR"));
            W42.setGroup(util.findGroupInSet("Wit"));
            W42.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W42);

            Article W43 = new Article(); //79
            W43.setExcelCode("WM0000NL");
            W43.setInsetLimit(4);
            W43.setInsetGram(400);
            W43.setPalletLimit(170);
            W43.setOrigin("NL");
            W43.setPalletType(dpa);
            W43.setCask(util.findCaskInSet("Emballage multi"));
            W43.setInset(util.findInsetInSet("ID0212TR"));
            W43.setGroup(util.findGroupInSet("Wit"));
            W43.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W43);

            Article W44 = new Article(); //89
            W44.setExcelCode("WF0000NL");
            W44.setInsetLimit(4);
            W44.setInsetGram(400);
            W44.setPalletLimit(170);
            W44.setOrigin("NL");
            W44.setPalletType(dpa);
            W44.setCask(util.findCaskInSet("Emballage multi"));
            W44.setInset(util.findInsetInSet("ID0212TR"));
            W44.setGroup(util.findGroupInSet("Wit"));
            W44.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W44);

            Article W45 = new Article(); //90
            W45.setExcelCode("WF0000NL");
            W45.setInsetLimit(4);
            W45.setInsetGram(175);
            W45.setPalletLimit(170);
            W45.setOrigin("NL");
            W45.setPalletType(dpa);
            W45.setCask(util.findCaskInSet("Emballage multi"));
            W45.setInset(util.findInsetInSet("ID0070XX"));
            W45.setGroup(util.findGroupInSet("Wit"));
            W45.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W45);

            Article W46 = new Article(); //91
            W46.setExcelCode("WF0000NL");
            W46.setInsetLimit(1);
            W46.setInsetGram(1250);
            W46.setPalletLimit(170);
            W46.setOrigin("NL");
            W46.setPalletType(dpa);
            W46.setCask(util.findCaskInSet("Emballage semi-multi"));
            W46.setGroup(util.findGroupInSet("Wit"));
            W46.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W46);

            Article W47 = new Article(); //92
            W47.setExcelCode("WM0000NL");
            W47.setInsetLimit(1);
            W47.setInsetGram(1250);
            W47.setPalletLimit(170);
            W47.setOrigin("NL");
            W47.setPalletType(dpa);
            W47.setCask(util.findCaskInSet("Emballage semi-multi"));
            W47.setGroup(util.findGroupInSet("Wit"));
            W47.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W47);

            Article W48 = new Article(); //93
            W48.setExcelCode("WF0000NL");
            W48.setInsetLimit(1);
            W48.setInsetGram(3000);
            W48.setPalletLimit(170);
            W48.setOrigin("NL");
            W48.setPalletType(dpa);
            W48.setCask(util.findCaskInSet("Emballage doos CHEF"));
            W48.setInset(util.findInsetInSet("ID2001BR"));
            W48.setGroup(util.findGroupInSet("Wit"));
            W48.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W48);

            Article W49 = new Article(); //94
            W49.setExcelCode("WM0000NL");
            W49.setInsetLimit(1);
            W49.setInsetGram(3000);
            W49.setPalletLimit(170);
            W49.setOrigin("NL");
            W49.setPalletType(dpa);
            W49.setCask(util.findCaskInSet("Emballage multi"));
            W49.setInset(util.findInsetInSet("Klasse II"));
            W49.setGroup(util.findGroupInSet("Wit"));
            W49.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W49);

            Article W50 = new Article(); //95
            W50.setExcelCode("WR0000NL");
            W50.setInsetLimit(2);
            W50.setInsetGram(750);
            W50.setPalletLimit(170);
            W50.setOrigin("NL");
            W50.setPalletType(dpa);
            W50.setCask(util.findCaskInSet("Emballage multi"));
            W50.setInset(util.findInsetInSet("ID0243BL"));
            W50.setGroup(util.findGroupInSet("Wit"));
            W50.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W50);

            Article W51 = new Article(); //96
            W51.setExcelCode("WS0000NL");
            W51.setInsetLimit(1);
            W51.setInsetGram(2500);
            W51.setPalletLimit(170);
            W51.setOrigin("NL");
            W51.setPalletType(dpa);
            W51.setCask(util.findCaskInSet("Emballage multi"));
            W51.setGroup(util.findGroupInSet("Wit"));
            W51.setSortType(util.findSortTypeInSet("Gesneden"));
            articleRepo.save(W51);

            Article W52 = new Article(); //99
            W52.setExcelCode("WM0000NL");
            W52.setInsetLimit(3);
            W52.setInsetGram(250);
            W52.setPalletLimit(170);
            W52.setOrigin("NL");
            W52.setPalletType(dpa);
            W52.setCask(util.findCaskInSet("Emballage multi"));
            W52.setInset(util.findInsetInSet("ID0014XX"));
            W52.setGroup(util.findGroupInSet("Wit"));
            W52.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W52);

            Article W53 = new Article(); //102
            W53.setExcelCode("WFL0000NL");
            W53.setInsetLimit(1);
            W53.setInsetGram(1800);
            W53.setPalletLimit(170);
            W53.setOrigin("NL");
            W53.setPalletType(dpa);
            W53.setCask(util.findCaskInSet("Emballage multi"));
            W53.setGroup(util.findGroupInSet("Wit"));
            W53.setSortType(util.findSortTypeInSet("Flats"));
            articleRepo.save(W53);

            Article W54 = new Article(); //104
            W54.setExcelCode("WF0000NL");
            W54.setInsetLimit(4);
            W54.setInsetGram(300);
            W54.setPalletLimit(170);
            W54.setOrigin("NL");
            W54.setPalletType(dpa);
            W54.setCask(util.findCaskInSet("Emballage multi"));
            W54.setInset(util.findInsetInSet("ID0190BL"));
            W54.setGroup(util.findGroupInSet("Wit"));
            W54.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W54);

            Article W55 = new Article(); //105
            W55.setExcelCode("WM0000NL");
            W55.setInsetLimit(4);
            W55.setInsetGram(300);
            W55.setPalletLimit(170);
            W55.setOrigin("NL");
            W55.setPalletType(dpa);
            W55.setCask(util.findCaskInSet("Emballage multi"));
            W55.setInset(util.findInsetInSet("ID0190BL"));
            W55.setGroup(util.findGroupInSet("Wit"));
            W55.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W55);

            Article W56 = new Article(); //106
            W56.setExcelCode("WM0000NL");
            W56.setInsetLimit(4);
            W56.setInsetGram(350);
            W56.setPalletLimit(170);
            W56.setOrigin("NL");
            W56.setPalletType(dpa);
            W56.setCask(util.findCaskInSet("Emballage multi"));
            W56.setInset(util.findInsetInSet("ID0710TR"));
            W56.setGroup(util.findGroupInSet("Wit"));
            W56.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W56);

            Article W57 = new Article(); //107
            W57.setExcelCode("WM0000NL");
            W57.setInsetLimit(2);
            W57.setInsetGram(400);
            W57.setPalletLimit(170);
            W57.setOrigin("NL");
            W57.setPalletType(dpa);
            W57.setCask(util.findCaskInSet("Emballage multi"));
            W57.setInset(util.findInsetInSet("ID0024XX"));
            W57.setGroup(util.findGroupInSet("Wit"));
            W57.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W57);

            Article W58 = new Article(); //108
            W58.setExcelCode("WM0000NL");
            W58.setInsetLimit(4);
            W58.setInsetGram(500);
            W58.setPalletLimit(170);
            W58.setOrigin("NL");
            W58.setPalletType(dpa);
            W58.setCask(util.findCaskInSet("Emballage multi"));
            W58.setInset(util.findInsetInSet("ID0208BL"));
            W58.setGroup(util.findGroupInSet("Wit"));
            W58.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W58);

            Article W59 = new Article(); //109
            W59.setExcelCode("WM0000NL");
            W59.setInsetLimit(4);
            W59.setInsetGram(500);
            W59.setPalletLimit(170);
            W59.setOrigin("NL");
            W59.setPalletType(dpa);
            W59.setCask(util.findCaskInSet("Emballage multi"));
            W59.setInset(util.findInsetInSet("ID0190TR"));
            W59.setGroup(util.findGroupInSet("Wit"));
            W59.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W59);

            Article W60 = new Article(); //110
            W60.setExcelCode("WM0000NL");
            W60.setInsetLimit(1);
            W60.setInsetGram(3000);
            W60.setPalletLimit(170);
            W60.setOrigin("NL");
            W60.setPalletType(dpa);
            W60.setCask(util.findCaskInSet("Emballage multi"));
            W60.setGroup(util.findGroupInSet("Wit"));
            W60.setSortType(util.findSortTypeInSet("Gesneden (35-45 mm)"));
            articleRepo.save(W60);

            Article W61 = new Article(); //111
            W61.setExcelCode("WM0000NL");
            W61.setInsetLimit(1);
            W61.setInsetGram(3000);
            W61.setPalletLimit(170);
            W61.setOrigin("NL");
            W61.setPalletType(dpa);
            W61.setCask(util.findCaskInSet("Emballage multi"));
            W61.setGroup(util.findGroupInSet("Wit"));
            W61.setSortType(util.findSortTypeInSet("Gesneden (45-55 mm)"));
            articleRepo.save(W61);

            Article W62 = new Article(); //112
            W62.setExcelCode("WM0000NL");
            W62.setInsetLimit(1);
            W62.setInsetGram(2500);
            W62.setPalletLimit(170);
            W62.setOrigin("NL");
            W62.setPalletType(dpa);
            W62.setCask(util.findCaskInSet("Emballage multi"));
            W62.setGroup(util.findGroupInSet("Wit"));
            W62.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W62);

            Article W63 = new Article(); //113
            W63.setExcelCode("WR0000DE");
            W63.setInsetLimit(1);
            W63.setInsetGram(2500);
            W63.setPalletLimit(170);
            W63.setOrigin("DE");
            W63.setPalletType(dpa);
            W63.setCask(util.findCaskInSet("Emballage multi"));
            W63.setGroup(util.findGroupInSet("Wit"));
            W63.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W63);

            Article W64 = new Article(); //114
            W64.setExcelCode("WR0000NL");
            W64.setInsetLimit(3);
            W64.setInsetGram(400);
            W64.setPalletLimit(170);
            W64.setOrigin("NL");
            W64.setPalletType(dpa);
            W64.setCask(util.findCaskInSet("Emballage multi"));
            W64.setGroup(util.findGroupInSet("Wit"));
            W64.setSortType(util.findSortTypeInSet("Reuzen"));
            articleRepo.save(W64);

            Article W65 = new Article(); //115
            W65.setExcelCode("WM0000NL");
            W65.setInsetLimit(1);
            W65.setInsetGram(3000);
            W65.setPalletLimit(170);
            W65.setOrigin("NL");
            W65.setPalletType(dpa);
            W65.setInset(util.findInsetInSet("ID2001BR"));
            W65.setGroup(util.findGroupInSet("Wit"));
            W65.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W65);

            Article W66 = new Article(); //116
            W66.setExcelCode("WF0000NL");
            W66.setInsetLimit(4);
            W66.setInsetGram(500);
            W66.setPalletLimit(170);
            W66.setOrigin("NL");
            W66.setPalletType(dpa);
            W66.setCask(util.findCaskInSet("Emballage multi"));
            W66.setInset(util.findInsetInSet("ID0190BL"));
            W66.setGroup(util.findGroupInSet("Wit"));
            W66.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W66);

            Article W67 = new Article(); //117
            W67.setExcelCode("WF0000NL");
            W67.setInsetLimit(4);
            W67.setInsetGram(500);
            W67.setPalletLimit(170);
            W67.setOrigin("NL");
            W67.setPalletType(dpa);
            W67.setCask(util.findCaskInSet("Emballage multi"));
            W67.setInset(util.findInsetInSet("ID0190BL"));
            W67.setGroup(util.findGroupInSet("Wit"));
            W67.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(W67);

            Article W68 = new Article(); //118
            W68.setExcelCode("WM0000NL");
            W68.setInsetLimit(4);
            W68.setInsetGram(500);
            W68.setPalletLimit(200);
            W68.setOrigin("NL");
            W68.setPalletType(dpa);
            W68.setCask(util.findCaskInSet("Emballage multi"));
            W68.setInset(util.findInsetInSet("ID0208TR"));
            W68.setGroup(util.findGroupInSet("Wit"));
            W68.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(W68);


            // endregion

            // region Kastanje
            Article K1 = new Article(); //3
            K1.setExcelCode("IN0003NL");
            K1.setInsetLimit(1);
            K1.setInsetGram(2500);
            K1.setPalletLimit(170);
            K1.setOrigin("NL");
            K1.setPalletType(dpa);
            K1.setCask(util.findCaskInSet("Emballage multi"));
            K1.setGroup(util.findGroupInSet("Kastanje"));
            K1.setSortType(util.findSortTypeInSet("Industrie (2.3.80)"));
            articleRepo.save(K1);

            Article K2 = new Article(); //4
            K2.setExcelCode("IN0004NL");
            K2.setInsetLimit(6);
            K2.setInsetGram(250);
            K2.setPalletLimit(170);
            K2.setOrigin("NL");
            K2.setPalletType(dpa);
            K2.setCask(util.findCaskInSet("Emballage multi"));
            K2.setInset(util.findInsetInSet("ID0052ST"));
            K2.setGroup(util.findGroupInSet("Kastanje"));
            K2.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K2);

            Article K3 = new Article(); //5
            K3.setExcelCode("IN0005NL");
            K3.setInsetLimit(6);
            K3.setInsetGram(250);
            K3.setPalletLimit(170);
            K3.setOrigin("NL");
            K3.setPalletType(dpa);
            K3.setCask(util.findCaskInSet("Emballage multi"));
            K3.setInset(util.findInsetInSet("ID0051BL"));
            K3.setGroup(util.findGroupInSet("Kastanje"));
            K3.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K3);

            Article K4 = new Article(); //6
            K4.setExcelCode("IN0006NL");
            K4.setInsetLimit(4);
            K4.setInsetGram(400);
            K4.setPalletLimit(170);
            K4.setOrigin("NL");
            K4.setPalletType(dpa);
            K4.setCask(util.findCaskInSet("Emballage multi"));
            K4.setInset(util.findInsetInSet("ID0190GR"));
            K4.setGroup(util.findGroupInSet("Kastanje"));
            K4.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K4);

            Article K5 = new Article(); //7
            K5.setExcelCode("IN0007NL");
            K5.setInsetLimit(4);
            K5.setInsetGram(250);
            K5.setPalletLimit(170);
            K5.setOrigin("NL");
            K5.setPalletType(dpa);
            K5.setCask(util.findCaskInSet("Emballage multi"));
            K5.setInset(util.findInsetInSet("ID0705ST"));
            K5.setGroup(util.findGroupInSet("Kastanje"));
            K5.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K5);

            Article K6 = new Article(); //8
            K6.setExcelCode("IN0008NL");
            K6.setInsetLimit(4);
            K6.setInsetGram(350);
            K6.setPalletLimit(170);
            K6.setOrigin("NL");
            K6.setPalletType(dpa);
            K6.setCask(util.findCaskInSet("Emballage multi"));
            K6.setInset(util.findInsetInSet("ID0710GR"));
            K6.setGroup(util.findGroupInSet("Kastanje"));
            K6.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K6);

            Article K7 = new Article(); //9
            K7.setExcelCode("IN0009NL");
            K7.setInsetLimit(3);
            K7.setInsetGram(600);
            K7.setPalletLimit(170);
            K7.setOrigin("NL");
            K7.setPalletType(dpa);
            K7.setCask(util.findCaskInSet("Emballage multi"));
            K7.setInset(util.findInsetInSet("ID0220GR"));
            K7.setGroup(util.findGroupInSet("Kastanje"));
            K7.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K7);

            Article K8 = new Article(); //10
            K8.setExcelCode("IN0010NL");
            K8.setInsetLimit(1);
            K8.setInsetGram(3000);
            K8.setPalletLimit(170);
            K8.setOrigin("NL");
            K8.setPalletType(dpa);
            K8.setCask(util.findCaskInSet("Emballage multi"));
            K8.setGroup(util.findGroupInSet("Kastanje"));
            K8.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K8);

            Article K9 = new Article(); //11
            K9.setExcelCode("IN00011NL");
            K9.setInsetLimit(6);
            K9.setInsetGram(250);
            K9.setPalletLimit(170);
            K9.setOrigin("NL");
            K9.setPalletType(dpa);
            K9.setCask(util.findCaskInSet("Emballage multi"));
            K9.setInset(util.findInsetInSet("ID0052ST"));
            K9.setGroup(util.findGroupInSet("Kastanje"));
            K9.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K9);

            Article K10 = new Article(); //13
            K10.setExcelCode("IN00011NL");
            K10.setInsetLimit(6);
            K10.setInsetGram(250);
            K10.setPalletLimit(170);
            K10.setOrigin("NL");
            K10.setPalletType(dpa);
            K10.setCask(util.findCaskInSet("Emballage multi"));
            K10.setInset(util.findInsetInSet("ID0051BL"));
            K10.setGroup(util.findGroupInSet("Kastanje"));
            K10.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K10);

            Article K11 = new Article(); //14
            K11.setExcelCode("IN00011NL");
            K11.setInsetLimit(6);
            K11.setInsetGram(250);
            K11.setPalletLimit(170);
            K11.setOrigin("NL");
            K11.setPalletType(dpa);
            K11.setCask(util.findCaskInSet("Emballage multi"));
            K11.setInset(util.findInsetInSet("ID0190TR"));
            K11.setGroup(util.findGroupInSet("Kastanje"));
            K11.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K11);

            Article K12 = new Article(); //15
            K12.setExcelCode("IN00011NL");
            K12.setInsetLimit(4);
            K12.setInsetGram(400);
            K12.setPalletLimit(170);
            K12.setOrigin("NL");
            K12.setPalletType(dpa);
            K12.setCask(util.findCaskInSet("Emballage multi"));
            K12.setInset(util.findInsetInSet("ID0190BL"));
            K12.setGroup(util.findGroupInSet("Kastanje"));
            K12.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K12);

            Article K13 = new Article(); //15
            K13.setExcelCode("IN00011NL");
            K13.setInsetLimit(4);
            K13.setInsetGram(400);
            K13.setPalletLimit(170);
            K13.setOrigin("NL");
            K13.setPalletType(dpa);
            K13.setCask(util.findCaskInSet("Emballage multi"));
            K13.setInset(util.findInsetInSet("ID0190GR"));
            K13.setGroup(util.findGroupInSet("Kastanje"));
            K13.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K13);

            Article K14 = new Article(); //16
            K14.setExcelCode("IN00011NL");
            K14.setInsetLimit(4);
            K14.setInsetGram(250);
            K14.setPalletLimit(170);
            K14.setOrigin("NL");
            K14.setPalletType(dpa);
            K14.setCask(util.findCaskInSet("Emballage multi"));
            K14.setInset(util.findInsetInSet("ID0705ST"));
            K14.setGroup(util.findGroupInSet("Kastanje"));
            K14.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K14);

            Article K15 = new Article(); //17
            K15.setExcelCode("IN00011NL");
            K15.setInsetLimit(4);
            K15.setInsetGram(350);
            K15.setPalletLimit(170);
            K15.setOrigin("NL");
            K15.setPalletType(dpa);
            K15.setCask(util.findCaskInSet("Emballage multi"));
            K15.setInset(util.findInsetInSet("ID0710GR"));
            K15.setGroup(util.findGroupInSet("Kastanje"));
            K15.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K15);

            Article K16 = new Article(); //18
            K16.setExcelCode("IN00011NL");
            K16.setInsetLimit(3);
            K16.setInsetGram(600);
            K16.setPalletLimit(170);
            K16.setOrigin("NL");
            K16.setPalletType(dpa);
            K16.setCask(util.findCaskInSet("Emballage multi"));
            K16.setInset(util.findInsetInSet("ID0710GR"));
            K16.setGroup(util.findGroupInSet("Kastanje"));
            K16.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K16);

            Article K17 = new Article(); //19
            K17.setExcelCode("IN00011NL");
            K17.setInsetLimit(4);
            K17.setInsetGram(120);
            K17.setPalletLimit(170);
            K17.setOrigin("NL");
            K17.setPalletType(dpa);
            K17.setCask(util.findCaskInSet("Emballage multi"));
            K17.setInset(util.findInsetInSet("ID0705ST"));
            K17.setGroup(util.findGroupInSet("Kastanje"));
            K17.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K17);

            Article K18 = new Article(); //20
            K18.setExcelCode("IN00011NL");
            K18.setInsetLimit(1);
            K18.setPalletLimit(170);
            K18.setOrigin("NL");
            K18.setPalletType(dpa);
            K18.setCask(util.findCaskInSet("Emballage multi"));
            K18.setGroup(util.findGroupInSet("Kastanje"));
            K18.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K18);

            Article K19 = new Article(); //21
            K19.setExcelCode("IN00011NL");
            K19.setInsetLimit(1);
            K19.setInsetGram(2500);
            K19.setPalletLimit(170);
            K19.setOrigin("NL");
            K19.setPalletType(dpa);
            K19.setCask(util.findCaskInSet("Emballage multi"));
            K19.setGroup(util.findGroupInSet("Kastanje"));
            K19.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K19);

            Article K20 = new Article(); //22
            K20.setExcelCode("IN00011NL");
            K20.setInsetLimit(1);
            K20.setInsetGram(3000);
            K20.setPalletLimit(170);
            K20.setOrigin("NL");
            K20.setPalletType(dpa);
            K20.setCask(util.findCaskInSet("Emballage multi"));
            K20.setGroup(util.findGroupInSet("Kastanje"));
            K20.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K20);

            Article K21 = new Article(); //70
            K21.setExcelCode("IN00011NL");
            K21.setInsetLimit(1);
            K21.setInsetGram(1000);
            K21.setPalletLimit(170);
            K21.setOrigin("NL");
            K21.setPalletType(dpa);
            K21.setCask(util.findCaskInSet("Emballage multi"));
            K21.setInset(util.findInsetInSet("ID0052ST"));
            K21.setGroup(util.findGroupInSet("Kastanje"));
            articleRepo.save(K21);

            Article K22 = new Article(); //74
            K22.setExcelCode("IN00011NL");
            K22.setInsetLimit(4);
            K22.setInsetGram(250);
            K22.setPalletLimit(170);
            K22.setOrigin("NL");
            K22.setPalletType(dpa);
            K22.setCask(util.findCaskInSet("Emballage multi"));
            K22.setInset(util.findInsetInSet("ID1001BR"));
            K22.setGroup(util.findGroupInSet("Kastanje"));
            K22.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K22);

            Article K23 = new Article(); //76
            K23.setExcelCode("IN00011NL");
            K23.setInsetLimit(1);
            K23.setInsetGram(3000);
            K23.setPalletLimit(170);
            K23.setOrigin("NL");
            K23.setPalletType(dpa);
            K23.setCask(util.findCaskInSet("Emballage multi"));
            K23.setGroup(util.findGroupInSet("Kastanje"));
            K23.setSortType(util.findSortTypeInSet("Gesneden"));
            articleRepo.save(K23);

            Article K24 = new Article(); //85
            K24.setExcelCode("KMI0000NL");
            K24.setInsetLimit(1);
            K24.setInsetGram(175);
            K24.setPalletLimit(170);
            K24.setOrigin("NL");
            K24.setPalletType(dpa);
            K24.setCask(util.findCaskInSet("Emballage multi"));
            K24.setInset(util.findInsetInSet("ID0190ST"));
            K24.setGroup(util.findGroupInSet("Kastanje"));
            K24.setSortType(util.findSortTypeInSet("Mini"));
            articleRepo.save(K24);

            Article K25 = new Article(); //86
            K25.setExcelCode("KF0000NL");
            K25.setInsetLimit(1);
            K25.setInsetGram(1250);
            K25.setPalletLimit(170);
            K25.setOrigin("NL");
            K25.setPalletType(dpa);
            K25.setCask(util.findCaskInSet("Emballage semi-multi"));
            K25.setGroup(util.findGroupInSet("Kastanje"));
            K25.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K25);

            Article K26 = new Article(); //87
            K26.setExcelCode("KF0000NL");
            K26.setInsetLimit(1);
            K26.setInsetGram(2500);
            K26.setPalletLimit(170);
            K26.setOrigin("NL");
            K26.setPalletType(dpa);
            K26.setCask(util.findCaskInSet("Emballage multi"));
            K26.setGroup(util.findGroupInSet("Kastanje"));
            K26.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K26);

            Article K27 = new Article(); //88
            K27.setExcelCode("KM0000NL");
            K27.setInsetLimit(2);
            K27.setInsetGram(1000);
            K27.setPalletLimit(170);
            K27.setOrigin("NL");
            K27.setPalletType(dpa);
            K27.setCask(util.findCaskInSet("Emballage multi"));
            K27.setInset(util.findInsetInSet("ID0243BL"));
            K27.setGroup(util.findGroupInSet("Kastanje"));
            K27.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(K27);

            Article K28 = new Article(); //100
            K28.setExcelCode("KF0000NL");
            K28.setInsetLimit(6);
            K28.setInsetGram(250);
            K28.setPalletLimit(170);
            K28.setOrigin("NL");
            K28.setPalletType(dpa);
            K28.setCask(util.findCaskInSet("Emballage multi"));
            K28.setInset(util.findInsetInSet("ID0050BL"));
            K28.setGroup(util.findGroupInSet("Kastanje"));
            K28.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K28);

            Article K29 = new Article(); //101
            K29.setExcelCode("KF0000NL");
            K29.setInsetLimit(4);
            K29.setInsetGram(400);
            K29.setPalletLimit(170);
            K29.setOrigin("NL");
            K29.setPalletType(dpa);
            K29.setCask(util.findCaskInSet("Emballage multi"));
            K29.setInset(util.findInsetInSet("ID0212TR"));
            K29.setGroup(util.findGroupInSet("Kastanje"));
            K29.setSortType(util.findSortTypeInSet("Fijn"));
            articleRepo.save(K29);
            // endregion

            // region Oesterzwam
            Article OE1 = new Article(); //23
            OE1.setExcelCode("OE0000NL");
            OE1.setInsetLimit(4);
            OE1.setInsetGram(250);
            OE1.setPalletLimit(170);
            OE1.setOrigin("NL");
            OE1.setPalletType(dpa);
            OE1.setCask(util.findCaskInSet("Emballage multi"));
            OE1.setInset(util.findInsetInSet("ID0190GR"));
            OE1.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE1);

            Article OE2 = new Article(); //24
            OE2.setExcelCode("OE0000NL");
            OE2.setInsetLimit(4);
            OE2.setInsetGram(200);
            OE2.setPalletLimit(170);
            OE2.setOrigin("NL");
            OE2.setPalletType(dpa);
            OE2.setCask(util.findCaskInSet("Emballage multi"));
            OE2.setInset(util.findInsetInSet("ID0208TR"));
            OE2.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE2);

            Article OE3 = new Article(); //25
            OE3.setExcelCode("OE0000NL");
            OE3.setInsetLimit(1);
            OE3.setInsetGram(1500);
            OE3.setPalletLimit(170);
            OE3.setOrigin("NL");
            OE3.setPalletType(dpa);
            OE3.setCask(util.findCaskInSet("Emballage multi"));
            OE3.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE3);

            Article OE4 = new Article(); //26
            OE4.setExcelCode("OE0000NL");
            OE4.setInsetLimit(1);
            OE4.setInsetGram(1500);
            OE4.setPalletLimit(170);
            OE4.setOrigin("PL");
            OE4.setPalletType(dpa);
            OE4.setCask(util.findCaskInSet("Emballage multi"));
            OE4.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE4);

            Article OE5 = new Article(); //27
            OE5.setExcelCode("OE0000NL");
            OE5.setInsetLimit(1);
            OE5.setInsetGram(1500);
            OE5.setPalletLimit(170);
            OE5.setOrigin("NL");
            OE5.setPalletType(dpa);
            OE5.setCask(util.findCaskInSet("Emballage multi"));
            OE5.setGroup(util.findGroupInSet("Oesterzwam"));
            OE5.setType(util.findTypeInSet("Tros"));
            articleRepo.save(OE5);

            Article OE6 = new Article(); //67
            OE6.setExcelCode("OE0000NL");
            OE6.setInsetLimit(1);
            OE6.setInsetGram(1000);
            OE6.setPalletLimit(170);
            OE6.setOrigin("NL");
            OE6.setPalletType(dpa);
            OE6.setCask(util.findCaskInSet("Emballage multi"));
            OE6.setInset(util.findInsetInSet("ID0052ST"));
            OE6.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE6);

            Article OE7 = new Article(); //72
            OE7.setExcelCode("OE0000NL");
            OE7.setInsetLimit(1);
            OE7.setInsetGram(2000);
            OE7.setPalletLimit(170);
            OE7.setOrigin("NL");
            OE7.setPalletType(dpa);
            OE7.setCask(util.findCaskInSet("Emballage multi"));
            OE7.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE7);

            Article OE8 = new Article(); //80
            OE8.setExcelCode("OE0000NL");
            OE8.setInsetLimit(4);
            OE8.setInsetGram(200);
            OE8.setPalletLimit(170);
            OE8.setOrigin("NL");
            OE8.setPalletType(dpa);
            OE8.setCask(util.findCaskInSet("Emballage multi"));
            OE8.setInset(util.findInsetInSet("ID0208BL"));
            OE8.setGroup(util.findGroupInSet("Oesterzwam"));
            articleRepo.save(OE8);
            // endregion

            // region Portabella
            Article PO1 = new Article(); //28
            PO1.setExcelCode("IN00011NL");
            PO1.setInsetLimit(4);
            PO1.setInsetGram(2);
            PO1.setPalletLimit(170);
            PO1.setOrigin("NL");
            PO1.setPalletType(dpa);
            PO1.setCask(util.findCaskInSet("Emballage multi"));
            PO1.setInset(util.findInsetInSet("ID0710TR"));
            PO1.setGroup(util.findGroupInSet("Portabella"));
            articleRepo.save(PO1);

            Article PO2 = new Article(); //29
            PO2.setExcelCode("IN00011NL");
            PO2.setInsetLimit(1);
            PO2.setInsetGram(1500);
            PO2.setPalletLimit(170);
            PO2.setOrigin("NL");
            PO2.setPalletType(dpa);
            PO2.setCask(util.findCaskInSet("Emballage multi"));
            PO2.setGroup(util.findGroupInSet("Portabella"));
            articleRepo.save(PO2);
            // endregion

            // region Shiitake
            Article SHB1 = new Article(); //30
            SHB1.setExcelCode("SH0000NL");
            SHB1.setInsetLimit(6);
            SHB1.setInsetGram(150);
            SHB1.setPalletLimit(170);
            SHB1.setOrigin("NL");
            SHB1.setPalletType(dpa);
            SHB1.setCask(util.findCaskInSet("Emballage multi"));
            SHB1.setInset(util.findInsetInSet("ID0052ST"));
            SHB1.setGroup(util.findGroupInSet("Shiitake"));
            SHB1.setBiologic(true);
            SHB1.setAdditionalInfo("NL-BIO-01 - FP");
            articleRepo.save(SHB1);

            Article SHB2 = new Article(); //31
            SHB2.setExcelCode("SH0000NL");
            SHB2.setInsetLimit(1);
            SHB2.setInsetGram(1500);
            SHB2.setPalletLimit(170);
            SHB2.setOrigin("NL");
            SHB2.setPalletType(dpa);
            SHB2.setCask(util.findCaskInSet("Emballage multi"));
            SHB2.setGroup(util.findGroupInSet("Shiitake"));
            SHB2.setBiologic(true);
            SHB2.setAdditionalInfo("NL-BIO-01 - FP");
            articleRepo.save(SHB2);

            Article SHB3 = new Article(); //81
            SHB3.setExcelCode("SH0000NL");
            SHB3.setInsetLimit(4);
            SHB3.setInsetGram(150);
            SHB3.setPalletLimit(170);
            SHB3.setOrigin("NL");
            SHB3.setPalletType(dpa);
            SHB3.setCask(util.findCaskInSet("Emballage multi"));
            SHB3.setInset(util.findInsetInSet("ID0710TR"));
            SHB3.setGroup(util.findGroupInSet("Shiitake"));
            SHB3.setBiologic(true);
            SHB3.setAdditionalInfo("NL-BIO-01 - FP");
            articleRepo.save(SHB2);

            Article SHB4 = new Article(); //82
            SHB4.setExcelCode("SH0000NL");
            SHB4.setInsetLimit(4);
            SHB4.setInsetGram(100);
            SHB4.setPalletLimit(170);
            SHB4.setOrigin("NL");
            SHB4.setPalletType(dpa);
            SHB4.setCask(util.findCaskInSet("Emballage multi"));
            SHB4.setInset(util.findInsetInSet("ID0342TR"));
            SHB4.setGroup(util.findGroupInSet("Shiitake"));
            SHB4.setBiologic(true);
            SHB4.setAdditionalInfo("NL-BIO-01 - FP");
            articleRepo.save(SHB4);

            Article SHB5 = new Article(); //103
            SHB5.setExcelCode("WFL0000NL");
            SHB5.setInsetLimit(1);
            SHB5.setInsetGram(1500);
            SHB5.setPalletLimit(170);
            SHB5.setOrigin("NL");
            SHB5.setPalletType(dpa);
            SHB5.setCask(util.findCaskInSet("Emballage multi"));
            SHB5.setGroup(util.findGroupInSet("Shiitake"));
            articleRepo.save(SHB5);
            // endregion

            // region Beukenzwam
            Article BEU1 = new Article(); //68
            BEU1.setExcelCode("IN0002NL");
            BEU1.setInsetLimit(1);
            BEU1.setInsetGram(1000);
            BEU1.setPalletLimit(170);
            BEU1.setOrigin("NL");
            BEU1.setPalletType(plastic);
            BEU1.setCask(util.findCaskInSet("Emballage multi"));
            BEU1.setInset(util.findInsetInSet("ID0052ST"));
            BEU1.setGroup(util.findGroupInSet("Beukenzwam"));
            articleRepo.save(BEU1);

            Article BEU2 = new Article(); //69
            BEU2.setExcelCode("IN0002NL");
            BEU2.setInsetLimit(1);
            BEU2.setInsetGram(1500);
            BEU2.setPalletLimit(170);
            BEU2.setOrigin("NL");
            BEU2.setPalletType(plastic);
            BEU2.setCask(util.findCaskInSet("Emballage multi"));
            BEU2.setInset(util.findInsetInSet("ID0052ST"));
            BEU2.setGroup(util.findGroupInSet("Beukenzwam"));
            articleRepo.save(BEU2);
            // endregion

            // region Oyster
            Article OM1 = new Article(); //83
            OM1.setExcelCode("OM0000HU");
            OM1.setInsetLimit(1);
            OM1.setInsetGram(1500);
            OM1.setPalletLimit(170);
            OM1.setOrigin("HU");
            OM1.setPalletType(euro);
            OM1.setCask(util.findCaskInSet("M6 Holland Crates"));
            OM1.setGroup(util.findGroupInSet("Oyster Mushroom"));
            OM1.setSortType(util.findSortTypeInSet("Medium"));
            OM1.setColor(util.findColorInSet("Grey"));
            OM1.setType(util.findTypeInSet("Leaves"));
            articleRepo.save(OM1);

            Article OM2 = new Article(); //84
            OM2.setExcelCode("OM0000HU");
            OM2.setInsetLimit(1);
            OM2.setInsetGram(1500);
            OM2.setPalletLimit(170);
            OM2.setOrigin("HU");
            OM2.setPalletType(euro);
            OM2.setCask(util.findCaskInSet("M6 Holland Crates"));
            OM2.setGroup(util.findGroupInSet("Oyster Mushroom"));
            OM2.setSortType(util.findSortTypeInSet("Small"));
            OM2.setColor(util.findColorInSet("Grey"));
            OM2.setType(util.findTypeInSet("Leaves"));
            articleRepo.save(OM2);
            // endregion

            // region Afkeur
            Article AF1 = new Article(); //97
            AF1.setExcelCode("AF0000NL");
            AF1.setInsetLimit(1);
            AF1.setInsetGram(10000);
            AF1.setPalletLimit(170);
            AF1.setOrigin("NL");
            AF1.setPalletType(dpa);
            AF1.setCask(util.findCaskInSet("Emballage industrie"));
            AF1.setGroup(util.findGroupInSet("Afk(2-2-60)"));
            AF1.setSortType(util.findSortTypeInSet("Middel"));
            articleRepo.save(AF1);

            Article AF2 = new Article(); //98
            AF2.setExcelCode("AF0000NL");
            AF2.setInsetLimit(1);
            AF2.setInsetGram(10000);
            AF2.setPalletLimit(170);
            AF2.setOrigin("NL");
            AF2.setPalletType(dpa);
            AF2.setCask(util.findCaskInSet("Emballage industrie"));
            AF2.setGroup(util.findGroupInSet("Afk(2-3-80)"));
            AF2.setSortType(util.findSortTypeInSet("Extra fijn"));
            articleRepo.save(AF2);
            // endregion

            // region Industrie
            Article IN1 = new Article(); //119
            IN1.setExcelCode("WM0000NL");
            IN1.setInsetLimit(1);
            IN1.setInsetGram(10000);
            IN1.setPalletLimit(45);
            IN1.setOrigin("NL");
            IN1.setPalletType(euro);
            IN1.setCask(util.findCaskInSet("Emballage industrie"));
            IN1.setInset(util.findInsetInSet("IN0050NL"));
            IN1.setGroup(util.findGroupInSet("Wit"));
            IN1.setSortType(util.findSortTypeInSet("Industrie (2.3.80)"));
            articleRepo.save(IN1);
            // endregion

            log.info("Articles done seeding");
        }
    }
}
