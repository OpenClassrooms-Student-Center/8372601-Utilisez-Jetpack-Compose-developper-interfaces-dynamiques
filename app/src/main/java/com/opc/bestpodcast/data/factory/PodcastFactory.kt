package com.opc.bestpodcast.data.factory

import com.opc.bestpodcast.data.model.Category
import com.opc.bestpodcast.data.model.Podcast

object PodcastFactory {
    fun makePodcasts(): List<Podcast> =
        listOf(
            Podcast(
                id = "P1",
                title = "Changements",
                category = Category(2, "Industrie"),
                description =
                "Changements explore les défis d'un monde en transformation rapide : perturbations écologiques, avancées technologiques, changements géopolitiques, et bien plus encore. À travers des interviews avec des experts et des acteurs de premier plan, nous vous aidons à comprendre et à anticiper les transformations de demain.",
                logoUrl = "https://course.oc-static.com/8372601/Changements.png.webp",
            ),
            Podcast(
                id = "P2",
                title = "Le numérique vert",
                category = Category(1, "Technologie"),
                description = "Le numérique vert vous propose une réflexion bimensuelle sur un numérique plus durable et responsable. Abordant des sujets comme l'éco-conception et l'impact environnemental, ce podcast apporte une dose d'optimisme et d'humour tout en traitant des enjeux sérieux du climat et de la technologie.",
                logoUrl = "https://course.oc-static.com/8372601/Numerique_Vert.png.webp",
            ),
            Podcast(
                id = "P3",
                title = "Vos chemins inspirants",
                category = Category(6, "Style de vie"),
                description = "Vos chemins inspirants vous invite chaque semaine à prendre du recul sur les défis actuels et votre propre parcours. Pendant 30 minutes, découvrez la perspective unique d'une personne influente ou inspirante pour vous aider à voir le monde sous un nouvel angle.",
                logoUrl = "https://course.oc-static.com/8372601/CheminsInspirants.png.webp",
            ),
            Podcast(
                id = "P4",
                title = "Vous avez osé",
                category = Category(6, "Style de vie"),
                description =
                "Vous avez osé décortique le succès des individus qui ont pris des risques significatifs. Qu'ils soient entrepreneurs, sportifs ou artistes, plongez dans leur quotidien et découvrez leurs histoires sans filtre. Ce podcast vise à comprendre leur mentalité, leur organisation et leurs outils, offrant une véritable leçon sur les réussites et les échecs.",
                logoUrl = "https://course.oc-static.com/8372601/Vousavezose.png.webp",
            ),
            Podcast(
                id = "P5",
                title = "Entre vous et moi",
                category = Category(3, "Société"),
                description =
                "Entre vous et moi est un podcast qui explore notre société à travers les relations : à soi-même, aux autres et à la nature. Chaque mardi, rejoignez Grégory Pouy dans une conversation enrichissante avec une personne passionnée et éclairée, pour mieux comprendre les liens qui nous unissent.",
                logoUrl = "https://course.oc-static.com/8372601/Entrevousetmoi.png.webp",
            ),
            Podcast(
                id = "P6",
                title = "La planète et vous !",
                category = Category(2, "Industrie"),
                description =
                "Bienvenue sur La planète et vous !, un podcast qui décrypte les solutions au changement climatique. Chaque épisode de 30 minutes présente une industrie et un expert discutant des actions concrètes pour réduire les émissions de gaz à effet de serre. Découvrez des sujets variés tels que la construction, l'agriculture, et le transport, abordés de manière positive et inspirante.",
                logoUrl = "https://course.oc-static.com/8372601/Laplaneteetvous.png.webp",
            ),
            Podcast(
                id = "P7",
                title = "Futur Connecté",
                category = Category(1, "Technologie"),
                description =
                "Futur Connecté explore les avancées technologiques et leur impact sur notre quotidien. Chaque semaine, plongez dans les innovations émergentes telles que l'intelligence artificielle, la réalité augmentée et l'Internet des objets, et découvrez comment elles transforment notre manière de travailler, de communiquer et de vivre.",
                logoUrl = "https://course.oc-static.com/8372601/Futurconnecte",
            ),
            Podcast(
                id = "P8",
                title = "Savoir & Innovations",
                category = Category(1, "Technologie"),
                description =
                "Savoir & Innovations explore les dernières avancées scientifiques et technologiques. De la biotechnologie à l'astronomie en passant par la physique quantique, chaque épisode vous plonge au cœur des découvertes les plus fascinantes du moment, avec des interviews d'experts du monde entier.",
                logoUrl = "https://course.oc-static.com/8372601/SavoirInnovations.webp",
            ),
            Podcast(
                id = "P9",
                title = "Découverte Nature",
                category = Category(3, "Société"),
                description =
                "Découverte Nature vous invite à explorer la beauté et la complexité du monde naturel. À travers des récits captivants et des témoignages d'explorateurs et de biologistes, découvrez la diversité des écosystèmes, des espèces animales et des phénomènes naturels qui façonnent notre planète.",
                logoUrl = "https://course.oc-static.com/8372601/DecouverteNature",
            ),
            Podcast(
                id = "P10",
                title = "Révolutions Urbaines",
                category = Category(6, "Style de vie"),
                description =
                "Révolutions Urbaines explore les défis et les solutions pour des villes durables. Chaque épisode met en lumière les initiatives locales et mondiales visant à améliorer la qualité de vie urbaine tout en réduisant l'empreinte écologique des zones urbaines. Découvrez les innovations en matière d'architecture, de transport et de gestion des déchets.",
                logoUrl = "https://course.oc-static.com/8372601/Revolutionsurbaines.webp",
            ),
            Podcast(
                id = "P11",
                title = "Éco Cuisine",
                category = Category(5, "Santé"),
                description =
                "Éco Cuisine explore le lien entre l'alimentation durable et la santé. Chaque semaine, découvrez des recettes à base d'ingrédients locaux et de saison, ainsi que des conseils pour réduire le gaspillage alimentaire et adopter une alimentation équilibrée et respectueuse de l'environnement.",
                logoUrl = "https://course.oc-static.com/8372601/Ecocuisine.webp",
            ),
            Podcast(
                id = "P12",
                title = "Innovation Sociale",
                category = Category(3, "Société"),
                description =
                "Innovation Sociale explore les projets et les initiatives qui transforment positivement la société. À travers des interviews inspirantes d'entrepreneurs sociaux, de chercheurs et d'activistes, découvrez comment l'innovation peut résoudre des problèmes sociaux complexes et promouvoir le changement durable.",
                logoUrl = "https://course.oc-static.com/8372601/+InnovationSociale.webp",
            )
        )
}
