package com.example.vtb_hackathon.entity

object FilterList {
    private const val PRIVATE_PERSON = 0
    private const val SELF_EMPLOYED = 1
    private const val SMALL_BUSINESS = 2
    private const val MASSIVE_BUSINESS = 3
    val filterList = listOf(
        FilterPresentation(
            PRIVATE_PERSON, listOf(
                "Все", "Кредиты", "Карты", "Ипотека", "Вклады",
            )
        ),
        FilterPresentation(
            SELF_EMPLOYED, listOf(
                "Все", "Расчетный счет", "Регистрация", "Формирование чека", "Оплата налога",
            )
        ),
        FilterPresentation(
            SMALL_BUSINESS, listOf(
                "Все", "Расчетный счет", "Бизнес карта", "Эквайринг", "Инкассация",
            )
        ),
        FilterPresentation(
            MASSIVE_BUSINESS, listOf(
                "Все", "Расчеты", "Эквайринг и СПБ", "Кредитование", "Депозитарий",
            )
        ),
    )
}

data class FilterPresentation(
    val id: Int,
    val serviceList: List<String>
)