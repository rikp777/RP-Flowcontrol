package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.model.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Slf4j
@Configuration
public class Seeder {
    @Bean
    CommandLineRunner commandLineRunner(
            PalletTypeSeeder palletTypeSeeder,
            CaskSeeder caskSeeder,
            GroupSeeder groupSeeder,
            TypeSeeder typeSeeder,
            ColorSeeder colorSeeder,
            ArticleSeeder articleSeeder,
            SortTypeSeeder sortTypeSeeder,
            InsetSeeder insetSeeder
    ) {
        return arts -> {
            UtilSeeder util = new UtilSeeder();

            // Pallet type
            Set<PalletType> palletTypes = palletTypeSeeder.run();
            util.setPalletTypes(palletTypes);
            log.info("Pallet types done seeding");

            // Color
            Set<Color> colors = colorSeeder.run();
            util.setColors(colors);
            log.info("Colors done seeding");

            // Cask
            Set<Cask> casks = caskSeeder.run(util);
            util.setCasks(casks);
            log.info("Casks done seeding");

            // Group
            Set<Group> groups = groupSeeder.run();
            util.setGroups(groups);
            log.info("Groups done seeding");

            // Sort Type
            Set<SortType> sortTypes = sortTypeSeeder.run();
            util.setSortTypes(sortTypes);
            log.info("Sort types done seeding");

            // Inset
            Set<Inset> insets = insetSeeder.run(util);
            util.setInsets(insets);
            log.info("Insets done seeding");

            // Types
            Set<Type> types = typeSeeder.run();
            util.setTypes(types);
            log.info("Types done seeding");




            articleSeeder.run(util);
            log.info("Articles done seeding");
        };
    }
}
