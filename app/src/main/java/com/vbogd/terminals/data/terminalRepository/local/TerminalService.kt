package com.vbogd.terminals.data.terminalRepository.local

import com.vbogd.terminals.domain.model.Terminal

class TerminalService {

    fun getTerminals(): List<Terminal> {
        return listOf(
            Terminal(
                id = "1",
                name = "Санкт-Петербург Парнас",
                address = "194292, Санкт-Петербург г, 1-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = false
            ),
            Terminal(
                id = "2",
                name = "Санкт-Петербург Колонтай",
                address = "194292, Санкт-Петербург г, 2-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = false
            ),
            Terminal(
                id = "3",
                name = "Санкт-Петербург Север 2",
                address = "194292, Санкт-Петербург г, 3-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = false
            ),
            Terminal(
                id = "4",
                name = "Санкт-Петербург Удельная",
                address = "194292, Санкт-Петербург г, 4-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = false
            ),
            Terminal(
                id = "5",
                name = "Москва Юг 2",
                address = "194292, Москва г, 1-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = true
            ),
            Terminal(
                id = "6",
                name = "Москва МКАД 69",
                address = "194292, Москва г, 2-й Верхний пер, дом № 12, Литера Б",
                workHours = "пн: 08:00-00:00; вт-пт: круглосуточно; сб: 00:00-20:00; вс: 09:00-20:00",
                distance = 2.2,
                imageAddress = "",
                direction = true
            )
        )
    }

}