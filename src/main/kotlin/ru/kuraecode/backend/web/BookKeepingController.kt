package ru.kuraecode.backend.web

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.kuraecode.backend.domain.BookKeepingInfo
import ru.kuraecode.backend.service.BookKeepingService
import ru.kuraecode.backend.web.dto.CreateBookKeepingInfoDto
import ru.kuraecode.backend.web.dto.UpdateBookKeepingInfoDto

@RestController
@RequestMapping("/api/bookkeeping")
class BookKeepingController(val bookKeepingService: BookKeepingService) {



    @PutMapping("/{id}")
    fun updateBookkeeping(@PathVariable id: Long, @RequestBody updateBookKeepingInfo: UpdateBookKeepingInfoDto): ResponseEntity<Void> {
        val toBookKeepingInfo = updateBookKeepingInfo.toBookKeepingInfo()
        bookKeepingService.updateBookkeeping(bookKeepingInfo = toBookKeepingInfo)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/{personId}")
    fun createBookkeepingInfo(@PathVariable personId: Long, @RequestBody createBookKeepingInfoDto: CreateBookKeepingInfoDto): ResponseEntity<BookKeepingInfo> {
        return ResponseEntity.ok(bookKeepingService.createBookkeepingForUser(
                personId = personId,
                bookKeepingInfo = createBookKeepingInfoDto.toBookKeepingInfo(),
                periodDate = createBookKeepingInfoDto.dayStart))
    }

    @DeleteMapping("/{id}")
    fun deleteBookkeepingInfo(@PathVariable id: Long): ResponseEntity<Void> {
        bookKeepingService.deleteBookkeeping(id)
        return ResponseEntity.ok().build()
    }
}