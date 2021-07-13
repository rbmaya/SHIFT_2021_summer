package ru.cft.shift2021summer.data.search_options

import ru.cft.shift2021summer.domain.search_options.SearchGroup
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchOptionsLocalDataSourceImpl @Inject constructor(
) : SearchOptionsDataSource {
    private val groups = mutableListOf(
        SearchGroup(
            name = "Brand",
            elements = listOf(
                "almay", "alva", "anna sui",
                "annabelle", "benefit", "boosh",
                "burt's bees", "butter london", "c'est moi",
                "cargo cosmetics", "china glaze", "clinique",
                "coastal classic creation", "colourpop", "covergirl",
                "dalish", "deciem", "dior", "dr. hauschka", "e.l.f.",
                "essie", "fenty", "glossier", "green people", "iman",
                "l'oreal", "lotus cosmetics usa", "maia's mineral galaxy",
                "marcelle", "marienatie", "maybelline", "milani",
                "mineral fusion", "misa", "mistura", "moov", "nudus", "nyx", "orly",
                "pacifica", "penny lane organics", "physicians formula", "piggy paint",
                "pure anada", "rejuva minerals", "revlon", "sally b's skin yummies",
                "salon perfect", "sante", "sinful colours", "smashbox", "stila",
                "suncoat", "w3llpeople", "wet n wild", "zorah",
                "zorah biocosmetiques"
            )
        ),
        SearchGroup(
            name = "Product Type",
            elements = listOf(
                "blush", "bronzer", "eyebrow", "eyeliner",
                "eyeshadow", "foundation", "lip liner",
                "lipstick", "mascara", "nail polish"
            )
        )
    )

    override fun getAllGroups(): List<SearchGroup> = groups


}