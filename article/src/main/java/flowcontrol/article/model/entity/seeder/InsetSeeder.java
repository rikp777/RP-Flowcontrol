package flowcontrol.article.model.entity.seeder;

import com.google.common.collect.Sets;
import flowcontrol.article.model.entity.Group;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.repository.InsetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Set;
import java.util.UUID;

@Configuration
@Slf4j
public class InsetSeeder {
    private int id = 1;

    @Autowired
    private final InsetRepository insetRepository;

    public InsetSeeder(InsetRepository insetRepository) {
        this.insetRepository = insetRepository;
    }

    private void message(Inset inset){
        boolean debug = true;
        if(debug)
            log.info("Inset seeder insert: " + this.id++ + " - " + inset.getName() + " | " +
                    "UUID: " + inset.getId()
            );
    }

    public Set<Inset> run(UtilSeeder util) {

        boolean seed = true;
        if(seed) {
            log.info("Inset seeding starting...");
            //region CH050
            if (insetRepository.findByExcelCode("ID0050TR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("025c3e78-e589-4397-ab6f-435194ee874f"));//1
                inset.setExcelCode("ID0050TR");
                inset.setName("CH050");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(756);
                inset.setDescription("CH050 Transparent (756/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0050BL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("02d30c39-6b84-46fb-832f-af7c6b431b15"));
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
                Inset inset = new Inset(UUID.fromString("0543d7b2-4d1e-451b-986a-a434e53ffa1a"));
                inset.setExcelCode("ID0051GR");
                inset.setName("CH051");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(770);
                inset.setDescription("CH051 Green (770/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0051BL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("0ba40c5d-db0e-4736-a7b4-5c52d38ea50a"));
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
                Inset inset = new Inset(UUID.fromString("0bff5338-8790-4970-99c4-6fe55d1c0c9a"));
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
                Inset inset = new Inset(UUID.fromString("0e8ef8f6-6604-4e41-ae4b-88def2689e64"));
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
                Inset inset = new Inset(UUID.fromString("221d166b-28ac-4d73-a58f-21969b3aead7"));
                inset.setExcelCode("ID0190TR");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH0190 Transparent (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0190BL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("27fcebd8-7bb8-4f8d-874d-13e50066ddec"));
                inset.setExcelCode("ID0190BL");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH0190 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0190GR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("33b1372a-061e-4191-acc6-aac040f0ee92"));
                inset.setExcelCode("ID0190GR");
                inset.setName("CH190");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH0190 Green (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0190ST").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("37974739-18d8-4f21-bd9a-0ff220fd4900"));
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
                Inset inset = new Inset(UUID.fromString("472f8b5f-2567-4500-8c65-f44ea5b1805a"));
                inset.setExcelCode("ID0190ST");
                inset.setName("CH205");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH190 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0205TR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("488c81f5-07ee-42e5-b97b-ff049ef30567"));
                inset.setExcelCode("ID0205TR");
                inset.setName("CH205");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH205 Transparent (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }
            if (insetRepository.findByExcelCode("ID0205BL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("536ed9aa-2618-4c5f-bad5-f2276ada4151"));
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
                Inset inset = new Inset(UUID.fromString("54870dcd-4b02-4ffb-95c5-ef130823c7b8"));
                inset.setExcelCode("ID0208BL");
                inset.setName("CH208");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH208 Blue (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0208BLA").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("60a0c728-7cc5-4484-942f-d4fe8e346860"));
                inset.setExcelCode("ID0208BLA");
                inset.setName("CH208");
                inset.setColor(util.findColorInSet("Black"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH208 Black (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0208TR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("61ec86c8-e952-4491-8df4-3535b4220f16"));
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
                Inset inset = new Inset(UUID.fromString("69f5a5a8-3b20-4de3-8048-0d9874784db9"));
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
                Inset inset = new Inset(UUID.fromString("6e02fcb9-264a-4366-8b8b-71a27fe6cd63"));
                inset.setExcelCode("ID0220BL");
                inset.setName("CH220");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(360);
                inset.setDescription("CH220 Blue (360/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0220GR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("76f702d1-d0eb-4c08-9bee-19ce9bca1933"));
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
                Inset inset = new Inset(UUID.fromString("79a3b1ff-6ea8-41ef-9a76-a17e3ca11fda"));
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
                Inset inset = new Inset(UUID.fromString("861289aa-0bed-4f11-84e3-4bc9bf8c8c12"));
                inset.setExcelCode("ID0243BL");
                inset.setName("CH243");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(310);
                inset.setDescription("CH243 Blue (310/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0243BLA").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("86c943ce-da82-4640-888d-e2c1130e7359"));
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
                Inset inset = new Inset(UUID.fromString("90427db9-5b4e-409a-b550-bad41573a792"));
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
                Inset inset = new Inset(UUID.fromString("957f1a17-f649-4f64-b0d4-f483f8dd0263"));
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
                Inset inset = new Inset(UUID.fromString("9b02d029-1a14-4e1b-b3fb-49e37e4c9cd7"));
                inset.setExcelCode("ID0710GR");
                inset.setName("CH710");
                inset.setColor(util.findColorInSet("Green"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("CH710 Green (640/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0710TR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("a1df6300-1c3b-4ea1-bd44-9f8fa24282f6"));
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
                Inset inset = new Inset(UUID.fromString("a41487eb-3184-4222-9c04-3503a220e61d"));
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
                Inset inset = new Inset(UUID.fromString("b66f846b-1120-4f7f-a773-37d09ba3d442"));
                inset.setExcelCode("ID1001BL");
                inset.setName("Kroon");
                inset.setColor(util.findColorInSet("Blue"));
                inset.setMaxInsetAmount(640);
                inset.setDescription("Kroon Blue 90H (320/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID1001BR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("b79f31b1-3c95-4874-8f5c-616bc69bf1eb"));
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
                Inset inset = new Inset(UUID.fromString("b961ed7d-15c5-480d-92f1-a40493fae22f"));
                inset.setExcelCode("ID2001BR");
                inset.setName("Chef Doos 30x40");
                inset.setColor(util.findColorInSet("Brown"));
                inset.setDescription("Chef Doos 30x40");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID2002BR").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("c1595a25-d4b0-498e-a793-02b16e9632ca"));
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
                Inset inset = new Inset(UUID.fromString("d302209a-841c-4d10-8403-dfec7bda6f63"));
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
                Inset inset = new Inset(UUID.fromString("d843a58f-ab19-4118-908c-32327ffce4ac"));
                inset.setExcelCode("ID0070XX");
                inset.setName("R Pet 175 gram");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setDescription("R Pet 175 gram");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("Klasse II").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("db454234-0254-493a-bf82-b4b25830ae9a"));
                inset.setExcelCode("Klasse II");
                inset.setName("Klasse II");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setDescription("Klasse II");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0014XX").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("de11721b-8146-4442-81d0-7e091df55742"));
                inset.setExcelCode("ID0014XX");
                inset.setName("383spl alupet");
                inset.setMaxInsetAmount(544);
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setDescription("383spl alupet (544/ds)");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("ID0024XX").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("de80fe11-6c21-47e6-a805-1eb1e7b4a1a2"));
                inset.setExcelCode("ID0024XX");
                inset.setName("GP100");
                inset.setColor(util.findColorInSet("Transparent"));
                inset.setDescription("GP100");
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0030NL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("e552fa0b-b224-491a-8716-bc603fc91919"));
                inset.setExcelCode("IN0030NL");
                inset.setName("2.2.60 [NL]");
                inset.setDescription("2.2.60 [NL]");
                inset.setColor(util.findColorInSet("Transparent"));
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0050NL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("ec094616-7253-40e2-b207-ffb328053895"));
                inset.setExcelCode("IN0050NL");
                inset.setName("2.3.80 [NL]");
                inset.setDescription("2.3.80 [NL]");
                inset.setColor(util.findColorInSet("Transparent"));
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0070NL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("f52f0c09-312e-4afe-b6b5-9762cf9f9394"));
                inset.setExcelCode("IN0070NL");
                inset.setName("2.3.100 [NL]");
                inset.setDescription("2.3.100 [NL]");
                inset.setColor(util.findColorInSet("Transparent"));
                insetRepository.save(inset);

                this.message(inset);
            }

            if (insetRepository.findByExcelCode("IN0080NL").isEmpty()) {
                Inset inset = new Inset(UUID.fromString("4427281e-2e5a-46aa-956a-587a02aa2db8"));
                inset.setExcelCode("IN0080NL");
                inset.setName("3.3.100 [NL]");
                inset.setDescription("3.3.100 [NL]");
                inset.setColor(util.findColorInSet("Transparent"));
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
