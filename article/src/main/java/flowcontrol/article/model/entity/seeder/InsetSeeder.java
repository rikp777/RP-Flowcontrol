package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.exception.ResourceNotFoundException;
import flowcontrol.article.model.entity.Cask;
import flowcontrol.article.model.entity.Color;
import flowcontrol.article.model.entity.Inset;
import flowcontrol.article.repository.CaskRepository;
import flowcontrol.article.repository.InsetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.events.Event;

import java.util.HashSet;
import java.util.Set;

@Configuration
public class InsetSeeder {

    @Autowired
    private final InsetRepository insetRepository;

    public InsetSeeder(InsetRepository insetRepository) {
        this.insetRepository = insetRepository;
    }


    public Set<Inset> run(UtilSeeder util) {
        // CH050
        Inset ID0050TR = new Inset();
        ID0050TR.setExcelCode("ID0050TR");
        ID0050TR.setName("CH050");
        ID0050TR.setColor(util.findColorInSet("Transparent"));
        ID0050TR.setMaxInsetAmount(756);
        ID0050TR.setDescription("CH050 Transparent (756/ds)");
        insetRepository.save(ID0050TR);

        Inset ID0050BL = new Inset();
        ID0050BL.setExcelCode("ID0050BL");
        ID0050BL.setName("CH050");
        ID0050BL.setColor(util.findColorInSet("Blue"));
        ID0050BL.setMaxInsetAmount(770);
        ID0050BL.setDescription("CH050 Blue (770/ds)");
        insetRepository.save(ID0050BL);

        // CH051
        Inset ID0051GR = new Inset();
        ID0051GR.setExcelCode("ID0051GR");
        ID0051GR.setName("CH051");
        ID0051GR.setColor(util.findColorInSet("Green"));
        ID0051GR.setMaxInsetAmount(770);
        ID0051GR.setDescription("CH051 Green (770/ds)");
        insetRepository.save(ID0051GR);

        Inset ID0051BL = new Inset();
        ID0051BL.setExcelCode("ID0051BL");
        ID0051BL.setName("CH051");
        ID0051BL.setColor(util.findColorInSet("Black"));
        ID0051BL.setMaxInsetAmount(770);
        ID0051BL.setDescription("CH051 Black (770/ds)");
        insetRepository.save(ID0051BL);

        // CH052
        Inset ID0052ST = new Inset();
        ID0052ST.setExcelCode("ID0052ST");
        ID0052ST.setName("CH052");
        ID0052ST.setColor(util.findColorInSet("Styro"));
        ID0052ST.setMaxInsetAmount(770);
        ID0052ST.setDescription("CH052 Styro (770/ds)");
        insetRepository.save(ID0052ST);

        // CH093
        Inset ID0093TR = new Inset();
        ID0093TR.setExcelCode("ID0093TR");
        ID0093TR.setName("CH093");
        ID0093TR.setColor(util.findColorInSet("Transparent"));
        ID0093TR.setMaxInsetAmount(700);
        ID0093TR.setDescription("CH093 Transparent (700/ds)");
        insetRepository.save(ID0093TR);

        // CH190
        Inset ID0190TR = new Inset();
        ID0190TR.setExcelCode("ID0190TR");
        ID0190TR.setName("CH190");
        ID0190TR.setColor(util.findColorInSet("Transparent"));
        ID0190TR.setMaxInsetAmount(640);
        ID0190TR.setDescription("CH0190 Transparent (640/ds)");
        insetRepository.save(ID0190TR);

        Inset ID0190BL = new Inset();
        ID0190BL.setExcelCode("ID0190BL");
        ID0190BL.setName("CH190");
        ID0190BL.setColor(util.findColorInSet("Black"));
        ID0190BL.setMaxInsetAmount(640);
        ID0190BL.setDescription("CH0190 Black (640/ds)");
        insetRepository.save(ID0190BL);

        Inset ID0190GR = new Inset();
        ID0190GR.setExcelCode("ID0190GR");
        ID0190GR.setName("CH190");
        ID0190GR.setColor(util.findColorInSet("Green"));
        ID0190GR.setMaxInsetAmount(640);
        ID0190GR.setDescription("CH0190 Green (640/ds)");
        insetRepository.save(ID0190GR);

        Inset ID0190ST = new Inset();
        ID0190ST.setExcelCode("ID0190ST");
        ID0190ST.setName("CH190");
        ID0190ST.setColor(util.findColorInSet("Styro"));
        ID0190ST.setMaxInsetAmount(640);
        ID0190ST.setDescription("CH190 Styro (640/ds)");
        insetRepository.save(ID0190ST);

        // CH205
        Inset ID0205BL = new Inset();
        ID0205BL.setExcelCode("ID0205BL");
        ID0205BL.setName("CH205");
        ID0205BL.setColor(util.findColorInSet("Black"));
        ID0205BL.setMaxInsetAmount(640);
        ID0205BL.setDescription("CH190 Black (640/ds)");
        insetRepository.save(ID0205BL);

        Inset ID0205TR = new Inset();
        ID0205TR.setExcelCode("ID0205TR");
        ID0205TR.setName("CH205");
        ID0205TR.setColor(util.findColorInSet("Transparent"));
        ID0205TR.setMaxInsetAmount(640);
        ID0205TR.setDescription("CH205 Transparent (640/ds)");
        insetRepository.save(ID0205TR);

        // CH208
        Inset ID0208BL = new Inset();
        ID0208BL.setExcelCode("ID0208BL");
        ID0208BL.setName("CH208");
        ID0208BL.setColor(util.findColorInSet("Blue"));
        ID0208BL.setMaxInsetAmount(640);
        ID0208BL.setDescription("CH208 Blue (640/ds)");
        insetRepository.save(ID0208BL);

        Inset ID0208BLA = new Inset();
        ID0208BLA.setExcelCode("ID0208BLA");
        ID0208BLA.setName("CH208");
        ID0208BLA.setColor(util.findColorInSet("Black"));
        ID0208BLA.setMaxInsetAmount(640);
        ID0208BLA.setDescription("CH208 Black (640/ds)");
        insetRepository.save(ID0208BLA);

        Inset ID0208TR = new Inset();
        ID0208TR.setExcelCode("ID0208TR");
        ID0208TR.setName("CH208");
        ID0208TR.setColor(util.findColorInSet("Transparent"));
        ID0208TR.setMaxInsetAmount(640);
        ID0208TR.setDescription("CH208 Transparent (640/ds)");
        insetRepository.save(ID0208TR);

        // CH212
        Inset ID0212TR = new Inset();
        ID0212TR.setExcelCode("ID0212TR");
        ID0212TR.setName("CH212");
        ID0212TR.setColor(util.findColorInSet("Transparent"));
        ID0212TR.setMaxInsetAmount(600);
        ID0212TR.setDescription("CH212 Transparent (600/ds)");
        insetRepository.save(ID0212TR);

        // CH220
        Inset ID0220BL = new Inset();
        ID0220BL.setExcelCode("ID0220BL");
        ID0220BL.setName("CH220");
        ID0220BL.setColor(util.findColorInSet("Blue"));
        ID0220BL.setMaxInsetAmount(360);
        ID0220BL.setDescription("CH220 Blue (360/ds)");
        insetRepository.save(ID0220BL);

        Inset ID0220GR = new Inset();
        ID0220GR.setExcelCode("ID0220GR");
        ID0220GR.setName("CH220");
        ID0220GR.setColor(util.findColorInSet("Green"));
        ID0220GR.setMaxInsetAmount(360);
        ID0220GR.setDescription("CH220 Green (360/ds)");
        insetRepository.save(ID0220GR);

        //CH239
        Inset ID0239BL = new Inset();
        ID0239BL.setExcelCode("ID0239BL");
        ID0239BL.setName("CH239");
        ID0239BL.setColor(util.findColorInSet("Black"));
        ID0239BL.setMaxInsetAmount(360);
        ID0239BL.setDescription("CH239 Black (360/ds)");
        insetRepository.save(ID0239BL);

        // CH243
        Inset ID0243BL = new Inset();
        ID0243BL.setExcelCode("ID0243BL");
        ID0243BL.setName("CH243");
        ID0243BL.setColor(util.findColorInSet("Blue"));
        ID0243BL.setMaxInsetAmount(310);
        ID0243BL.setDescription("CH243 Blue (310/ds)");
        insetRepository.save(ID0243BL);

        Inset ID0243BLA = new Inset();
        ID0243BLA.setExcelCode("ID0243BLA");
        ID0243BLA.setName("CH243");
        ID0243BLA.setColor(util.findColorInSet("Black"));
        ID0243BLA.setMaxInsetAmount(310);
        ID0243BLA.setDescription("CH243 Black (310/ds)");
        insetRepository.save(ID0243BLA);

        // CH342
        Inset ID0342TR = new Inset();
        ID0342TR.setExcelCode("ID0342TR");
        ID0342TR.setName("CH342");
        ID0342TR.setColor(util.findColorInSet("Transparent"));
        ID0342TR.setMaxInsetAmount(384);
        ID0342TR.setDescription("CH342 Transparent (384/ds)");
        insetRepository.save(ID0342TR);

        // CH705
        Inset ID0705ST = new Inset();
        ID0705ST.setExcelCode("ID0705ST");
        ID0705ST.setName("CH705");
        ID0705ST.setColor(util.findColorInSet("Styro"));
        ID0705ST.setMaxInsetAmount(640);
        ID0705ST.setDescription("CH705 Styro (640/ds)");
        insetRepository.save(ID0705ST);

        // CH710
        Inset ID0710GR = new Inset();
        ID0710GR.setExcelCode("ID0710GR");
        ID0710GR.setName("CH710");
        ID0710GR.setColor(util.findColorInSet("Green"));
        ID0710GR.setMaxInsetAmount(640);
        ID0710GR.setDescription("CH710 Green (640/ds)");
        insetRepository.save(ID0710GR);

        Inset ID0710TR = new Inset();
        ID0710TR.setExcelCode("ID0710TR");
        ID0710TR.setName("CH710");
        ID0710TR.setColor(util.findColorInSet("Transparent"));
        ID0710TR.setMaxInsetAmount(640);
        ID0710TR.setDescription("CH710 Transparent (640/ds)");
        insetRepository.save(ID0710TR);

        // CH865
        Inset ID0865TR = new Inset();
        ID0865TR.setExcelCode("ID0865TR");
        ID0865TR.setName("CH865");
        ID0865TR.setColor(util.findColorInSet("Transparent"));
        ID0865TR.setMaxInsetAmount(456);
        ID0865TR.setDescription("CH865 Transparent (456/ds)");
        insetRepository.save(ID0865TR);

        // Kroon
        Inset ID1001BL = new Inset();
        ID1001BL.setExcelCode("ID1001BL");
        ID1001BL.setName("Kroon");
        ID1001BL.setColor(util.findColorInSet("Blue"));
        ID1001BL.setMaxInsetAmount(640);
        ID1001BL.setDescription("Kroon Blue 90H (320/ds)");
        insetRepository.save(ID1001BL);

        Inset ID1001BR = new Inset();
        ID1001BR.setExcelCode("ID1001BR");
        ID1001BR.setName("Kroon");
        ID1001BR.setColor(util.findColorInSet("Brown"));
        ID1001BR.setMaxInsetAmount(640);
        ID1001BR.setDescription("Kroon Brown 63H (320/ds)");
        insetRepository.save(ID1001BR);

        // Doos
        Inset ID2001BR = new Inset();
        ID2001BR.setExcelCode("ID2001BR");
        ID2001BR.setName("Chef Doos 30x40");
        ID2001BR.setColor(util.findColorInSet("Brown"));
        ID2001BR.setDescription("Chef Doos 30x40");
        insetRepository.save(ID2001BR);

        Inset ID2002BR = new Inset();
        ID2002BR.setExcelCode("ID2002BR");
        ID2002BR.setName("Doos karton");
        ID2002BR.setColor(util.findColorInSet("Brown"));
        ID2002BR.setDescription("Doos karton");
        insetRepository.save(ID2002BR);

        // Crates
        Inset ID3001XX = new Inset();
        ID3001XX.setExcelCode("ID3001XX");
        ID3001XX.setName("Holland Crates");
        ID3001XX.setColor(util.findColorInSet("Brown"));
        ID3001XX.setDescription("Holland Crates");
        insetRepository.save(ID3001XX);


        //R Pet 175 gram
        Inset ID0070XX = new Inset();
        ID0070XX.setExcelCode("ID0070XX");
        ID0070XX.setName("R Pet 175 gram");
        ID0070XX.setColor(util.findColorInSet("Transparent"));
        ID0070XX.setDescription("R Pet 175 gram");
        insetRepository.save(ID0070XX);

        Inset KlasseII = new Inset();
        KlasseII.setExcelCode("Klasse II");
        KlasseII.setName("Klasse II");
        KlasseII.setDescription("Klasse II");
        insetRepository.save(KlasseII);

        Inset ID0014XX = new Inset();
        ID0014XX.setExcelCode("ID0014XX");
        ID0014XX.setName("383spl alupet");
        ID0014XX.setMaxInsetAmount(544);
        ID0014XX.setDescription("383spl alupet (544/ds)");
        insetRepository.save(ID0014XX);

        Inset ID0024XX = new Inset();
        ID0024XX.setExcelCode("ID0024XX");
        ID0024XX.setName("GP100");
        ID0024XX.setDescription("GP100");
        insetRepository.save(ID0024XX);

        Inset IN0030NL = new Inset();
        IN0030NL.setExcelCode("IN0030NL");
        IN0030NL.setName("2.2.60 [NL]");
        IN0030NL.setDescription("2.2.60 [NL]");
        insetRepository.save(IN0030NL);

        Inset IN0050NL = new Inset();
        IN0050NL.setExcelCode("IN0050NL");
        IN0050NL.setName("2.3.80 [NL]");
        IN0050NL.setDescription("2.3.80 [NL]");
        insetRepository.save(IN0050NL);

        Inset IN0070NL = new Inset();
        IN0070NL.setExcelCode("IN0070NL");
        IN0070NL.setName("2.3.100 [NL]");
        IN0070NL.setDescription("2.3.100 [NL]");
        insetRepository.save(IN0070NL);

        Inset IN0080NL = new Inset();
        IN0080NL.setExcelCode("IN0080NL");
        IN0080NL.setName("3.3.100 [NL]");
        IN0080NL.setDescription("3.3.100 [NL]");
        insetRepository.save(IN0080NL);

        return new HashSet<>(insetRepository.findAll());
    }
}
