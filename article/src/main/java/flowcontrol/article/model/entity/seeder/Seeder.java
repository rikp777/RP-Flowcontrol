package flowcontrol.article.model.entity.seeder;

import flowcontrol.article.model.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;


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
            var util = new UtilSeeder();

            // Pallet type
            Set<PalletType> palletTypes = palletTypeSeeder.run();
            util.setPalletTypes(palletTypes);


            // Color
            Set<Color> colors = colorSeeder.run();
            util.setColors(colors);


            // Cask
            Set<Cask> casks = caskSeeder.run(util);
            util.setCasks(casks);


            // Group
            Set<Group> groups = groupSeeder.run();
            util.setGroups(groups);

            // Sort Type
            Set<SortType> sortTypes = sortTypeSeeder.run();
            util.setSortTypes(sortTypes);


            // Inset
            Set<Inset> insets = insetSeeder.run(util);
            util.setInsets(insets);


            // Types
            Set<Type> types = typeSeeder.run();
            util.setTypes(types);

            articleSeeder.run(util);

        };
    }
}
