package com.example.domaci1.repository

import com.example.domaci1.model.Cat

val DataFile = listOf(
    Cat(
        name = "Abyssinian",
        alternativeNames = listOf("Aby", "Abby", "Bunny Cat"),
        description = "The Abyssinian is a breed of domestic short-haired cat with a distinctive 'ticked' tabby coat, in which individual hairs are banded with different colors.",
        personalityTraits = listOf("Active", "Energetic", "Independent", "Intelligent", "Gentle"),
        origin = "Ethiopia",
        lifeSpan = "12-15 years",
        size = "Medium",
        isRare = "No",
        wikiUrl = "https://en.wikipedia.org/wiki/Abyssinian_cat"
    ),
    Cat(
        name = "American Bobtail",
        alternativeNames = listOf("Bobtail", "Bob", "American Longhair"),
        description = "The American Bobtail is an uncommon breed of domestic cat which was developed in the late 1960s.",
        personalityTraits = listOf("Intelligent", "Interactive", "Lively", "Playful", "Sensitive"),
        origin = "United States",
        lifeSpan = "11-15 years",
        size = "Medium",
        isRare = "No",
        wikiUrl = "https://en.wikipedia.org/wiki/American_Bobtail"
    ),
    Cat(
        name = "American Curl",
        alternativeNames = listOf("Curl", "Curlie", "AC"),
        description = "The American Curl is a breed of cat characterized by its unusual ears, which curl back from the face toward the center of the back of the skull.",
        personalityTraits = listOf("Affectionate", "Curious", "Intelligent", "Social", "Playful"),
        origin = "United States",
        lifeSpan = "12-16 years",
        size = "Medium",
        isRare = "No",
        wikiUrl = "https://en.wikipedia.org/wiki/American_Curl"
    ),
    Cat(
        name = "American Shorthair",
        alternativeNames = listOf("Domestic Shorthair", "ASH", "DSH", "American Domestic"),
        description = "The American Shorthair is a breed of domestic cat believed to be descended from European cats brought to North America by early settlers to protect valuable cargo from mice and rats.",
        personalityTraits = listOf("Affectionate", "Curious", "Gentle", "Intelligent", "Playful"),
        origin = "United States",
        lifeSpan = "15-20 years",
        size = "Medium",
        isRare = "No",
        wikiUrl = "https://en.wikipedia.org/wiki/American_Shorthair"
    ),
)