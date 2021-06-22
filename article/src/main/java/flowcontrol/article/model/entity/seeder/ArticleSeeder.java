package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.model.entity.Article;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.PalletType;
import flowcontrol.article.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class ArticleSeeder {
    @Autowired
    private final ArticleRepository articleRepo;


    private int id = 0;

    public ArticleSeeder(ArticleRepository articleRepo) {
        this.articleRepo = articleRepo;
    }


    private void message(Article article){
        boolean debug = true;
        if(debug)
            log.info("Article seeder insert: " + this.id++ + " - " + article.getExcelCode());
    }

    public void run(UtilSeeder util) {
        boolean seed = true;
        if(seed) {
            Color brown = util.findColorInSet("Brown");
            Color white = util.findColorInSet("White");
            Color grey = util.findColorInSet("Grey");
            Color pink = util.findColorInSet("Pink");
            Color yellow = util.findColorInSet("Yellow");

            PalletType dpa = util.findPalletTypeInSet("DPA");
            PalletType plastic = util.findPalletTypeInSet("Plastic");
            PalletType euro = util.findPalletTypeInSet("Euro");

            Cask emballageMulti = util.findCaskInSet("Emballage multi");

            log.info("Article seeding starting...");

            // region Wit
            if (articleRepo.findByExcelCode("WI0001NL").isEmpty()) {
                Article article = new Article(); //1
                article.setExcelCode("WI0001NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Industrie (3.3.100)"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WI0002NL").isEmpty()) {
                Article article = new Article(); //2
                article.setExcelCode("WI0002NL");
                article.setInsetLimit(1);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(plastic);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Industrie"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0003NL").isEmpty()) {
                Article article = new Article(); //32
                article.setExcelCode("WF0003NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0004NL").isEmpty()) {
                Article article = new Article(); //33
                article.setExcelCode("WF0004NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0050BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0005NL").isEmpty()) {
                Article article = new Article(); //34
                article.setExcelCode("WF0005NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0006NL").isEmpty()) {
                Article article = new Article(); //35
                article.setExcelCode("WF0006NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0007NL").isEmpty()) {
                Article article = new Article(); //36
                article.setExcelCode("WF0007NL");
                article.setInsetLimit(2);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0205BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0008NL").isEmpty()) {
                Article article = new Article(); //37
                article.setExcelCode("WF0008NL");
                article.setInsetLimit(3);
                article.setInsetGram(600);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0220BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0009NL").isEmpty()) {
                Article article = new Article(); //38
                article.setExcelCode("WF0009NL");
                article.setInsetLimit(1);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0010NL").isEmpty()) {
                Article article = new Article(); //38
                article.setExcelCode("WF0010NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0011NL").isEmpty()) {
                Article article = new Article(); //39
                article.setExcelCode("WF0011NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0012NL").isEmpty()) {
                Article article = new Article(); //40
                article.setExcelCode("WF0012NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0013NL").isEmpty()) {
                Article article = new Article(); //41
                article.setExcelCode("WM0013NL");
                article.setInsetLimit(4);
                article.setInsetGram(150);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0705ST"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0014NL").isEmpty()) {
                Article article = new Article(); //42
                article.setExcelCode("WM0014NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0093TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0015NL").isEmpty()) {
                Article article = new Article(); //43
                article.setExcelCode("WM0015NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0220GR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0016NL").isEmpty()) {
                Article article = new Article(); //44
                article.setExcelCode("WM0016NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0017NL").isEmpty()) {
                Article article = new Article(); //45
                article.setExcelCode("WM0017NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0018NL").isEmpty()) {
                Article article = new Article(); //46
                article.setExcelCode("WM0018NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0205BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0019NL").isEmpty()) {
                Article article = new Article(); //47
                article.setExcelCode("WM0019NL");
                article.setInsetLimit(2);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0243BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0020NL").isEmpty()) {
                Article article = new Article(); //48
                article.setExcelCode("WM0020NL");
                article.setInsetLimit(3);
                article.setInsetGram(600);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0220BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0021NL").isEmpty()) {
                Article article = new Article(); //49
                article.setExcelCode("WM0021NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0021NL").isEmpty()) {
                Article article = new Article(); //50
                article.setExcelCode("WM0022NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0023NL").isEmpty()) {
                Article article = new Article(); //51
                article.setExcelCode("WM0023NL");
                article.setInsetLimit(4);
                article.setInsetGram(100);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setInset(util.findInsetInSet("ID0710TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0024NL").isEmpty()) {
                Article article = new Article(); //52
                article.setExcelCode("WM0024NL");
                article.setInsetLimit(1);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0025NL").isEmpty()) {
                Article article = new Article(); //53
                article.setExcelCode("WM0025NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0026NL").isEmpty()) {
                Article article = new Article(); //54
                article.setExcelCode("WM0026NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0027PL").isEmpty()) {
                Article article = new Article(); //55
                article.setExcelCode("WM0027PL");
                article.setInsetLimit(8);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("PL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0028NL").isEmpty()) {
                Article article = new Article(); //56
                article.setExcelCode("WR0028NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0050BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0029NL").isEmpty()) {
                Article article = new Article(); //57
                article.setExcelCode("WR0029NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0030NL").isEmpty()) {
                Article article = new Article(); //58
                article.setExcelCode("WR0030NL");
                article.setInsetLimit(2);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0243BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0031NL").isEmpty()) {
                Article article = new Article(); //59
                article.setExcelCode("WR0031NL");
                article.setInsetLimit(1);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0032NL").isEmpty()) {
                Article article = new Article(); //60
                article.setExcelCode("WR0032NL");
                article.setInsetLimit(1);
                article.setInsetGram(2000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0033NLBL").isEmpty()) {
                Article article = new Article(); //61
                article.setExcelCode("WR0033NLBL");
                article.setInsetLimit(1);
                article.setInsetGram(2000);
                article.setPalletLimit(170);
                article.setOrigin("NL/BL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0034NLGR").isEmpty()) {
                Article article = new Article(); //62
                article.setExcelCode("WR0034NLGR");
                article.setInsetLimit(1);
                article.setInsetGram(2000);
                article.setPalletLimit(170);
                article.setOrigin("NL/GR");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WEF0035NL").isEmpty()) {
                Article article = new Article(); //63
                article.setExcelCode("WEF0035NL");
                article.setInsetLimit(2);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0243BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Extra fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WEF0035NL").isEmpty()) {
                Article article = new Article(); //64
                article.setExcelCode("WEF0036NL");
                article.setInsetLimit(1);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Extra fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WEF0037NL").isEmpty()) {
                Article article = new Article(); //65
                article.setExcelCode("WEF0037NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Extra fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WEF0038NL").isEmpty()) {
                Article article = new Article(); //66
                article.setExcelCode("WEF0038NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Extra fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0039NL").isEmpty()) {
                Article article = new Article(); //71
                article.setExcelCode("WM0039NL");
                article.setInsetLimit(4);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0040NL").isEmpty()) {
                Article article = new Article(); //73
                article.setExcelCode("WM0040NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID1001BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0040NL").isEmpty()) {
                Article article = new Article(); //75
                article.setExcelCode("WM0040NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage doos CHEF"));
                article.setInset(util.findInsetInSet("ID2001BR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0042NL").isEmpty()) {
                Article article = new Article(); //78
                article.setExcelCode("WF0042NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0093TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0042NL").isEmpty()) {
                Article article = new Article(); //79
                article.setExcelCode("WM0043NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0212TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0044NL").isEmpty()) {
                Article article = new Article(); //89
                article.setExcelCode("WF0044NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0212TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0045NL").isEmpty()) {
                Article article = new Article(); //90
                article.setExcelCode("WF0045NL");
                article.setInsetLimit(4);
                article.setInsetGram(175);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0070XX"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0046NL").isEmpty()) {
                Article article = new Article(); //91
                article.setExcelCode("WF0046NL");
                article.setInsetLimit(1);
                article.setInsetGram(1250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage semi-multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0047NL").isEmpty()) {
                Article article = new Article(); //92
                article.setExcelCode("WM0047NL");
                article.setInsetLimit(1);
                article.setInsetGram(1250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage semi-multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0048NL").isEmpty()) {
                Article article = new Article(); //93
                article.setExcelCode("WF0048NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage doos CHEF"));
                article.setInset(util.findInsetInSet("ID2001BR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0049NL").isEmpty()) {
                Article article = new Article(); //94
                article.setExcelCode("WM0049NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("Klasse II"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0050NL").isEmpty()) {
                Article article = new Article(); //95
                article.setExcelCode("WR0050NL");
                article.setInsetLimit(2);
                article.setInsetGram(750);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0243BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0050NL").isEmpty()) {
                Article article = new Article(); //96
                article.setExcelCode("WR0050NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Gesneden"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0061NL").isEmpty()) {
                Article article = new Article(); //99
                article.setExcelCode("WM0061NL");
                article.setInsetLimit(3);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0014XX"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WFL0062NL").isEmpty()) {
                Article article = new Article(); //102
                article.setExcelCode("WFL0062NL");
                article.setInsetLimit(1);
                article.setInsetGram(1800);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Flats"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0063NL").isEmpty()) {
                Article article = new Article(); //104
                article.setExcelCode("WF0063NL");
                article.setInsetLimit(4);
                article.setInsetGram(300);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0064NL").isEmpty()) {
                Article article = new Article(); //105
                article.setExcelCode("WM0064NL");
                article.setInsetLimit(4);
                article.setInsetGram(300);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0065NL").isEmpty()) {
                Article article = new Article(); //106
                article.setExcelCode("WM0065NL");
                article.setInsetLimit(4);
                article.setInsetGram(350);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0065NL").isEmpty()){
                Article article = new Article(); //107
                article.setExcelCode("WM0066NL");
                article.setInsetLimit(2);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0024XX"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0065NL").isEmpty()){
                Article article = new Article(); //108
                article.setExcelCode("WM0067NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0208BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0068NL").isEmpty()){
                Article article = new Article(); //109
                article.setExcelCode("WM0068NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }
                
            if (articleRepo.findByExcelCode("WM0069NL").isEmpty()){
                Article article = new Article(); //110
                article.setExcelCode("WM0069NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Gesneden (35-45 mm)"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0070NL").isEmpty()){
                Article article = new Article(); //111
                article.setExcelCode("WM0070NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Gesneden (45-55 mm)"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0071NL").isEmpty()){
                Article article = new Article(); //112
                article.setExcelCode("WM0071NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0072DE").isEmpty()){
                Article article = new Article(); //113
                article.setExcelCode("WR0072DE");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("DE");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WR0073NL").isEmpty()){
                Article article = new Article(); //114
                article.setExcelCode("WR0073NL");
                article.setInsetLimit(3);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Reuzen"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0074NL").isEmpty()){
                Article article = new Article(); //115
                article.setExcelCode("WM0074NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setInset(util.findInsetInSet("ID2001BR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0075NL").isEmpty()){
                Article article = new Article(); //116
                article.setExcelCode("WF0075NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WF0076NL").isEmpty()){
                Article article = new Article(); //117
                article.setExcelCode("WF0076NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("WM0077NL").isEmpty()){
                Article article = new Article(); //118
                article.setExcelCode("WM0077NL");
                article.setInsetLimit(4);
                article.setInsetGram(500);
                article.setPalletLimit(200);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0208TR"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion
    
            // region Kastanje
            if (articleRepo.findByExcelCode("IN0003NL").isEmpty()){
                Article article = new Article(); //3
                article.setExcelCode("IN0003NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Industrie (2.3.80)"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0004NL").isEmpty()){
                Article article = new Article(); //4
                article.setExcelCode("IN0004NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0005NL").isEmpty()){
                Article article = new Article(); //5
                article.setExcelCode("IN0005NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0051BL"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0006NL").isEmpty()){
                Article article = new Article(); //6
                article.setExcelCode("IN0006NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190GR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0007NL").isEmpty()){
                Article article = new Article(); //7
                article.setExcelCode("IN0007NL");
                article.setInsetLimit(4);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0705ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0008NL").isEmpty()){
                Article article = new Article(); //8
                article.setExcelCode("IN0008NL");
                article.setInsetLimit(4);
                article.setInsetGram(350);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710GR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0009NL").isEmpty()){
                Article article = new Article(); //9
                article.setExcelCode("IN0009NL");
                article.setInsetLimit(3);
                article.setInsetGram(600);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0220GR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN0010NL").isEmpty()){
                Article article = new Article(); //10
                article.setExcelCode("IN0010NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00011NL").isEmpty()){
                Article article = new Article(); //11
                article.setExcelCode("IN00011NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00012NL").isEmpty()){
                Article article = new Article(); //13
                article.setExcelCode("IN00012NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0051BL"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00013NL").isEmpty()){
                Article article = new Article(); //14
                article.setExcelCode("IN00013NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190TR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00014NL").isEmpty()){
                Article article = new Article(); //15
                article.setExcelCode("IN00014NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190BL"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00015NL").isEmpty()){
                Article article = new Article(); //15
                article.setExcelCode("IN00015NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190GR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00016NL").isEmpty()){
                Article article = new Article(); //16
                article.setExcelCode("IN00016NL");
                article.setInsetLimit(4);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0705ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00017NL").isEmpty()){
                Article article = new Article(); //17
                article.setExcelCode("IN00017NL");
                article.setInsetLimit(4);
                article.setInsetGram(350);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710GR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00018NL").isEmpty()){
                Article article = new Article(); //18
                article.setExcelCode("IN00018NL");
                article.setInsetLimit(3);
                article.setInsetGram(600);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710GR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }
            
            if (articleRepo.findByExcelCode("IN00019NL").isEmpty()){
                Article article = new Article(); //19
                article.setExcelCode("IN00019NL");
                article.setInsetLimit(4);
                article.setInsetGram(120);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0705ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00020NL").isEmpty()){
                Article article = new Article(); //20
                article.setExcelCode("IN00020NL");
                article.setInsetLimit(1);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if (articleRepo.findByExcelCode("IN00021NL").isEmpty()){
                Article article = new Article(); //21
                article.setExcelCode("IN00021NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("IN00022NL").isEmpty()) {
                Article article = new Article(); //22
                article.setExcelCode("IN00022NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("IN00023NL").isEmpty()) {
                Article article = new Article(); //70
                article.setExcelCode("IN00023NL");
                article.setInsetLimit(1);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("IN00023NL").isEmpty()) {
                Article article = new Article(); //74
                article.setExcelCode("IN00023NL");
                article.setInsetLimit(4);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID1001BR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("IN00025NL").isEmpty()) {
                Article article = new Article(); //76
                article.setExcelCode("IN00025NL");
                article.setInsetLimit(1);
                article.setInsetGram(3000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Gesneden"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("KMI0001NL").isEmpty()) {
                Article article = new Article(); //85
                article.setExcelCode("KMI0001NL");
                article.setInsetLimit(1);
                article.setInsetGram(175);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190ST"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Mini"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("KF0002NL").isEmpty()) {
                Article article = new Article(); //86
                article.setExcelCode("KF0002NL");
                article.setInsetLimit(1);
                article.setInsetGram(1250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage semi-multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("KF0003NL").isEmpty()) {
                Article article = new Article(); //87
                article.setExcelCode("KF0003NL");
                article.setInsetLimit(1);
                article.setInsetGram(2500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("KM0004NL").isEmpty()) {
                Article article = new Article(); //88
                article.setExcelCode("KM0004NL");
                article.setInsetLimit(2);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0243BL"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("KF0005NL").isEmpty()) {
                Article article = new Article(); //100
                article.setExcelCode("KF0005NL");
                article.setInsetLimit(6);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0050BL"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("KF0000NL").isEmpty()) {
                Article article = new Article(); //101
                article.setExcelCode("KF0000NL");
                article.setInsetLimit(4);
                article.setInsetGram(400);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0212TR"));
                article.setGroup(util.findGroupInSet("Kastanje"));
                article.setSortType(util.findSortTypeInSet("Fijn"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion

            // region Oesterzwam
            if(articleRepo.findByExcelCode("OE0006NL").isEmpty()) {
                Article article = new Article(); //23
                article.setExcelCode("OE0006NL");
                article.setInsetLimit(4);
                article.setInsetGram(250);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0190GR"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("OE0009NL").isEmpty()) {
                Article article = new Article(); //24
                article.setExcelCode("OE0007NL");
                article.setInsetLimit(4);
                article.setInsetGram(200);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0208TR"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("OE0009NL").isEmpty()) {
                Article article = new Article(); //25
                article.setExcelCode("OE0009NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("OE0010NL").isEmpty()) {
                Article article = new Article(); //26
                article.setExcelCode("OE0010NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("PL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("OE0011NL").isEmpty()) {
                Article article = new Article(); //27
                article.setExcelCode("OE0011NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                article.setType(util.findTypeInSet("Tros"));
                articleRepo.save(article);

                this.message(article);
            }
            
            if(articleRepo.findByExcelCode("OE0012NL").isEmpty()) {
                Article article = new Article(); //67
                article.setExcelCode("OE0012NL");
                article.setInsetLimit(1);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }
            
            if(articleRepo.findByExcelCode("OE0013NL").isEmpty()) {
                Article article = new Article(); //72
                article.setExcelCode("OE0013NL");
                article.setInsetLimit(1);
                article.setInsetGram(2000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("OE0014NL").isEmpty()) {
                Article article = new Article(); //80
                article.setExcelCode("OE0014NL");
                article.setInsetLimit(4);
                article.setInsetGram(200);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0208BL"));
                article.setGroup(util.findGroupInSet("Oesterzwam"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion

            // region Portabella
            if(articleRepo.findByExcelCode("IN00056NL").isEmpty()) {
                Article article = new Article(); //28
                article.setExcelCode("IN00055NL");
                article.setInsetLimit(4);
                article.setInsetGram(2);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710TR"));
                article.setGroup(util.findGroupInSet("Portabella"));
                articleRepo.save(article);

                this.message(article);
            }
            
            if(articleRepo.findByExcelCode("IN00056NL").isEmpty()) {
                Article article = new Article(); //29
                article.setExcelCode("IN00056NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Portabella"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion

            // region Shiitake
            if(articleRepo.findByExcelCode("SH0058NL").isEmpty()) {
                Article article = new Article(); //30
                article.setExcelCode("SH0057NL");
                article.setInsetLimit(6);
                article.setInsetGram(150);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Shiitake"));
                article.setBiologic(true);
                article.setAdditionalInfo("NL-BIO-01 - FP");
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("SH0058NL").isEmpty()) {
                Article article = new Article(); //31
                article.setExcelCode("SH0058NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Shiitake"));
                article.setBiologic(true);
                article.setAdditionalInfo("NL-BIO-01 - FP");
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("SH0059NL").isEmpty()) {
                Article article = new Article(); //81
                article.setExcelCode("SH0059NL");
                article.setInsetLimit(4);
                article.setInsetGram(150);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0710TR"));
                article.setGroup(util.findGroupInSet("Shiitake"));
                article.setBiologic(true);
                article.setAdditionalInfo("NL-BIO-01 - FP");
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("SH0060NL").isEmpty()) {
                Article article = new Article(); //82
                article.setExcelCode("SH0060NL");
                article.setInsetLimit(4);
                article.setInsetGram(100);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0342TR"));
                article.setGroup(util.findGroupInSet("Shiitake"));
                article.setBiologic(true);
                article.setAdditionalInfo("NL-BIO-01 - FP");
                articleRepo.save(article);

                this.message(article);
            }
            
            if(articleRepo.findByExcelCode("WFL0061NL").isEmpty()) {
                Article article = new Article(); //103
                article.setExcelCode("WFL0061NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setGroup(util.findGroupInSet("Shiitake"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion

            // region Beukenzwam
            if(articleRepo.findByExcelCode("IN0062NL").isEmpty()) {
                Article article = new Article(); //68
                article.setExcelCode("IN0062NL");
                article.setInsetLimit(1);
                article.setInsetGram(1000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(plastic);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Beukenzwam"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("IN0063NL").isEmpty()) {
                Article article = new Article(); //69
                article.setExcelCode("IN0063NL");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(plastic);
                article.setCask(util.findCaskInSet("Emballage multi"));
                article.setInset(util.findInsetInSet("ID0052ST"));
                article.setGroup(util.findGroupInSet("Beukenzwam"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion

            // region Oyster
            if(articleRepo.findByExcelCode("OM0064HU").isEmpty()) {
                Article article = new Article(); //83
                article.setExcelCode("OM0064HU");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("HU");
                article.setPalletType(euro);
                article.setCask(util.findCaskInSet("M6 Holland Crates"));
                article.setGroup(util.findGroupInSet("Oyster Mushroom"));
                article.setSortType(util.findSortTypeInSet("Medium"));
                article.setColor(util.findColorInSet("Grey"));
                article.setType(util.findTypeInSet("Leaves"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("OM0065HU").isEmpty()) {
                Article article = new Article(); //84
                article.setExcelCode("OM0065HU");
                article.setInsetLimit(1);
                article.setInsetGram(1500);
                article.setPalletLimit(170);
                article.setOrigin("HU");
                article.setPalletType(euro);
                article.setCask(util.findCaskInSet("M6 Holland Crates"));
                article.setGroup(util.findGroupInSet("Oyster Mushroom"));
                article.setSortType(util.findSortTypeInSet("Small"));
                article.setColor(util.findColorInSet("Grey"));
                article.setType(util.findTypeInSet("Leaves"));
                articleRepo.save(article);

                this.message(article);
            }
            // endregion

            // region Afkeur
            if(articleRepo.findByExcelCode("AF0067NL").isEmpty()) {
                Article article = new Article(); //97
                article.setExcelCode("AF0066NL");
                article.setInsetLimit(1);
                article.setInsetGram(10000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage industrie"));
                article.setGroup(util.findGroupInSet("Afk(2-2-60)"));
                article.setSortType(util.findSortTypeInSet("Middel"));
                articleRepo.save(article);

                this.message(article);
            }

            if(articleRepo.findByExcelCode("AF0067NL").isEmpty()) {
                Article article = new Article(); //98
                article.setExcelCode("AF0067NL");
                article.setInsetLimit(1);
                article.setInsetGram(10000);
                article.setPalletLimit(170);
                article.setOrigin("NL");
                article.setPalletType(dpa);
                article.setCask(util.findCaskInSet("Emballage industrie"));
                article.setGroup(util.findGroupInSet("Afk(2-3-80)"));
                article.setSortType(util.findSortTypeInSet("Extra fijn"));
                articleRepo.save(article);
                
                this.message(article);
            }
            // endregion

            // region Industrie
            if(articleRepo.findByExcelCode("WM0168NL").isEmpty()) {
                Article article = new Article(); //119
                article.setExcelCode("WM0168NL");
                article.setInsetLimit(1);
                article.setInsetGram(10000);
                article.setPalletLimit(45);
                article.setOrigin("NL");
                article.setPalletType(euro);
                article.setCask(util.findCaskInSet("Emballage industrie"));
                article.setInset(util.findInsetInSet("IN0050NL"));
                article.setGroup(util.findGroupInSet("Wit"));
                article.setSortType(util.findSortTypeInSet("Industrie (2.3.80)"));
                articleRepo.save(article);
                
                this.message(article);
            }
            // endregion

            log.info("Articles seeding done, seeded: " +  this.id + " articles.");
        }else {
            log.info("Article seeding not required");
        }
    }
}
