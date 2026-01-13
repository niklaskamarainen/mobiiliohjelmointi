package com.example.week1.domain

import java.time.LocalDate

val mockTasks = listOf(
    Task(1, "Osta maitoa", "Käy kaupassa", 1, LocalDate.now().plusDays(1), false),
    Task(2, "Käytä koira", "Menen lenkille koiran kanssa", 1, LocalDate.now(), false),
    Task(3, "Salille", "Tee rintatreeni", 2, LocalDate.now().plusDays(1), false),
    Task(4, "Kolaus", "Kolaa piha", 1, LocalDate.now(), true),
    Task(5, "Kouluun", "Mene kouluun luennolle", 1, LocalDate.now().plusDays(2), false)
)
