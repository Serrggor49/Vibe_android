package com.gsu.vibe.composeScreens

import com.gsu.vibe.R

class FakeData {

    val sleep = listOf(
         Item(
            images = listOf((R.drawable.sleep_03_01b_concentration_on_breathing_prev)),
            type =  ItemType.Single
        ),
        ( Item(
            images = listOf(
                 (R.drawable.sleep_01_2b_endless_space_prev),
                 (R.drawable.nature_01_1b_prev),
                 (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_03_01b_concentration_on_breathing_prev),
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev),
            ), type =  ItemType.Quadruple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
    )


    val focus = listOf(
        Item(
            images = listOf((R.drawable.focus_03_2f_prev)),
            type =  ItemType.Single
        ),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_03_01b_concentration_on_breathing_prev),
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev),
            ), type =  ItemType.Quadruple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
    )


    val meditation = listOf(
        Item(
            images = listOf((R.drawable.focus_07_1f_prev)),
            type =  ItemType.Single
        ),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_03_01b_concentration_on_breathing_prev),
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev),
            ), type =  ItemType.Quadruple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
    )

    val nature = listOf(
        Item(
            images = listOf((R.drawable.focus_01_1b_prev)),
            type =  ItemType.Single
        ),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_03_01b_concentration_on_breathing_prev),
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev),
            ), type =  ItemType.Quadruple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
        ( Item(
            images = listOf(
                (R.drawable.sleep_01_2b_endless_space_prev),
                (R.drawable.nature_01_1b_prev),
                (R.drawable.focus_01_1b_prev)
            ), type =  ItemType.Triple
        )),
    )
}


data class Item(
    val type: ItemType,
    val images: List<Int> = emptyList(),
)

enum class ItemType { Single, Triple, Quadruple }