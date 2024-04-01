package com.example.domaci1.repository

import com.example.domaci1.model.Cat

val DataFile = listOf(
    Cat(
        name = "Abyssinian",
        alternativeNames = listOf("Aby", "Abby", "Abyssinian Shorthair"),
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        personalityTraits = listOf("Active", "Energetic", "Independent", "Intelligent", "Gentle")
    ),
    Cat(
        name = "American Bobtail",
        alternativeNames = listOf("Bobtail", "American Bob"),
        description = "American Bobtails are loving and incredibly intelligent cats possessing a distinctive wild appearance. They are extremely interactive cats that bond with their human family with great devotion.",
        personalityTraits = listOf("Intelligent", "Interactive", "Lively", "Playful", "Sensitive")
    ),
    Cat(
        name = "American Curl",
        alternativeNames = listOf("Curl"),
        description = "The American Curl is best known for its unique ears, which curl back from the face toward the center of the back of the skull. This breed is sociable, affectionate, curious, and good with children, dogs, and other animals.",
        personalityTraits = listOf("Affectionate", "Curious", "Intelligent", "Social", "Playful")
    ),
    Cat(
        name = "American Shorthair",
        alternativeNames = listOf("ASH", "American Domestic"),
        description = "The American Shorthair is a breed of domestic cat believed to be descended from European cats brought to North America by early settlers to protect valuable cargo from mice and rats. According to the Cat Fancier's Association, in 2012, it was the seventh most popular pedigreed cat in the United States.",
        personalityTraits = listOf("Affectionate", "Curious", "Intelligent", "Social", "Playful")
    ),
    Cat(
        name = "American Wirehair",
        alternativeNames = listOf("Wirehair"),
        description = "The American Wirehair is a breed of domestic cat originating in upstate New York. As of 2012, though the breed is well-known, it is ranked as the most rare of the 41 Cat Fanciers' Association breeds, with only 22 registered cats and ten breeders.",
        personalityTraits = listOf("Affectionate", "Curious", "Intelligent", "Social", "Playful")
    ),
)