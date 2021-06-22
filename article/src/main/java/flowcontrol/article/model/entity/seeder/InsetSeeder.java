package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.repository.InsetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
@Slf4j
public class InsetSeeder {
    private int id = 0;

    @Autowired
    private final InsetRepository insetRepository;

    public InsetSeeder(InsetRepository insetRepository) {
        this.insetRepository = insetRepository;
    }

    private void message(Inset inset){
        boolean debug = true;
        if(debug)
            log.info("Inset seeder insert: " + this.id++ + " - " + inset.getName());
    }

    public Set<Inset> run(UtilSeeder util) {

        boolean seed = true;
        if(seed) {
            log.info("Inset seeding starting...");
            //region CH050
            if (insetRepository.findByExcelCode("ID0050TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0050TR");
                inset.setName("CH050");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(756);
                inset.setDescription("CH050 Transparent (756/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0050BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0050BL");
                inset.setName("CH050");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(770);
                inset.setDescription("CH050 Blue (770/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH051
            if (insetRepository.findByExcelCode("ID0051GR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0051GR");
                inset.setName("CH051");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(770);
                inset.setDescription("CH051 Green (770/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0051BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0051BL");
                inset.setName("CH051");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(770);
                inset.setDescription("CH051 Black (770/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH052
            if (insetRepository.findByExcelCode("ID0052ST").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0052ST");
                inset.setName("CH052");
                inset.setColor(util.findColorInSet("Styro"));
                inset.setMaxInsetAmount(770);
                inset.setDescription("CH052 Styro (770/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH093
            if (insetRepository.findByExcelCode("ID0093TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0093TR");
                inset.setName("CH093");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(700);
                inset.setDescription("CH093 Transparent (700/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH190
            if (insetRepository.findByExcelCode("ID0190TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0190TR");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH0190 Transparent (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0190BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0190BL");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH0190 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0190GR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0190GR");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH0190 Green (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0190ST").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0190ST");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Styro"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH190 Styro (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH205
            if (insetRepository.findByExcelCode("ID0190ST").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0190ST");
                inset.setName("CH205");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH190 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0205TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0205TR");
                inset.setName("CH205");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH205 Transparent (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            if (insetRepository.findByExcelCode("ID0205BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0205BL");
                inset.setName("CH205");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH205 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH208
            if (insetRepository.findByExcelCode("ID0208BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0208BL");
                inset.setName("CH208");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH208 Blue (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0208BLA").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0208BLA");
                inset.setName("CH208");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH208 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0208TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0208TR");
                inset.setName("CH208");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH208 Transparent (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH212
            if (insetRepository.findByExcelCode("ID0212TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0212TR");
                inset.setName("CH212");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(600);
                inset.setDescription("CH212 Transparent (600/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH220
            if (insetRepository.findByExcelCode("ID0220BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0220BL");
                inset.setName("CH220");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(360);
                inset.setDescription("CH220 Blue (360/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0220GR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0220GR");
                inset.setName("CH220");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(360);
                inset.setDescription("CH220 Green (360/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH239
            if (insetRepository.findByExcelCode("ID0239BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0239BL");
                inset.setName("CH239");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(360);
                inset.setDescription("CH239 Black (360/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH243
            if (insetRepository.findByExcelCode("ID0243BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0243BL");
                inset.setName("CH243");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(310);
                inset.setDescription("CH243 Blue (310/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0243BLA").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0243BLA");
                inset.setName("CH243");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(310);
                inset.setDescription("CH243 Black (310/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH342
            if (insetRepository.findByExcelCode("ID0342TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0342TR");
                inset.setName("CH342");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(384);
                inset.setDescription("CH342 Transparent (384/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH705
            if (insetRepository.findByExcelCode("ID0705ST").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0705ST");
                inset.setName("CH705");
                inset.setColor(util.findColorInSet("Styro"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH705 Styro (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH710
            if (insetRepository.findByExcelCode("ID0710GR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0710GR");
                inset.setName("CH710");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH710 Green (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0710TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0710TR");
                inset.setName("CH710");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH710 Transparent (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region CH865
            if (insetRepository.findByExcelCode("ID0865TR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0865TR");
                inset.setName("CH865");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(456);
                inset.setDescription("CH865 Transparent (456/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region Kroon
            if (insetRepository.findByExcelCode("ID1001BL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID1001BL");
                inset.setName("Kroon");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("Kroon Blue 90H (320/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID1001BR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID1001BR");
                inset.setName("Kroon");
                inset.setColor(util.findColorInSet("Brown"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("Kroon Brown 63H (320/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion

            //region Doos
            if (insetRepository.findByExcelCode("ID2001BR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID2001BR");
                inset.setName("Chef Doos 30x40");
                inset.setColor(util.findColorInSet("Brown"));
                inset.setDescription("Chef Doos 30x40");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID2002BR").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID2002BR");
                inset.setName("Doos karton");
                inset.setColor(util.findColorInSet("Brown"));
                inset.setDescription("Doos karton");
                insetRepository.save(inset);

                this.message(inset);
            }

            //endregion

            //region Crates
            if (insetRepository.findByExcelCode("ID3001XX").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID3001XX");
                inset.setName("Holland Crates");
                inset.setColor(util.findColorInSet("Brown"));
                inset.setDescription("Holland Crates");
                insetRepository.save(inset);

                this.message(inset);
            }
            //endregion


            //region R Pet 175 gram
            if (insetRepository.findByExcelCode("ID0070XX").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0070XX");
                inset.setName("R Pet 175 gram");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setDescription("R Pet 175 gram");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("Klasse II").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("Klasse II");
                inset.setName("Klasse II");
                inset.setDescription("Klasse II");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0014XX").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0014XX");
                inset.setName("383spl alupet");
                inset.setMaxInsetAmount(544);
                inset.setDescription("383spl alupet (544/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0024XX").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("ID0024XX");
                inset.setName("GP100");
                inset.setDescription("GP100");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0030NL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("IN0030NL");
                inset.setName("2.2.60 [NL]");
                inset.setDescription("2.2.60 [NL]");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0050NL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("IN0050NL");
                inset.setName("2.3.80 [NL]");
                inset.setDescription("2.3.80 [NL]");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0070NL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("IN0070NL");
                inset.setName("2.3.100 [NL]");
                inset.setDescription("2.3.100 [NL]");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0080NL").isEmpty()) {
                Inset inset = new Inset();
                inset.setExcelCode("IN0080NL");
                inset.setName("3.3.100 [NL]");
                inset.setDescription("3.3.100 [NL]");
                insetRepository.save(inset);

                this.message(inset);
            }

            log.info("Inset seeding done, seeded: " +  this.id + " insets.");
        }else {
            log.info("Inset seeding not required");
        }

        return Sets.newHashSet(insetRepository.findAll());
    }
}
