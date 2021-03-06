package org.bumishi.techblog.api.interfaces.shard;

import org.bumishi.techblog.api.application.CatalogService;
import org.bumishi.techblog.api.domain.model.Book;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by xieqiang on 2017/1/6.
 */
@Component
public class BookDtoAssembler {

    @Autowired
    protected CatalogService catalogService;

    public BookDto toDto(Book book) {
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        bookDto.setCatalogDisplay(catalogService.getCatalog(book.getCatalog()).getLabel());
        return bookDto;
    }
}
