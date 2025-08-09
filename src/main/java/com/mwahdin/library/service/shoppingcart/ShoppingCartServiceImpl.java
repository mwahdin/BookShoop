package com.mwahdin.library.service.shoppingcart;


import com.mwahdin.library.dto.request.ShoppingCartRequest;
import com.mwahdin.library.dto.response.ShoppingCartResponse;
import com.mwahdin.library.model.*;
import com.mwahdin.library.repository.BookRepository;
import com.mwahdin.library.repository.FactorRepository;
import com.mwahdin.library.repository.ShoppingCartRepository;
import com.mwahdin.library.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final ShoppingCartRepository shoppingCartRepository;
    private final FactorRepository factorRepository;

    public ShoppingCartServiceImpl(BookRepository bookRepository, UserRepository userRepository, ShoppingCartRepository shoppingCartRepository, FactorRepository factorRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.shoppingCartRepository = shoppingCartRepository;
        this.factorRepository = factorRepository;
    }

    @Override
    @Transactional
    public ShoppingCartResponse addShoppingCart(ShoppingCartRequest shoppingCartRequest) {
        User userId = userRepository.findById(shoppingCartRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("USER.IS.NOT.FOUND"));
        Book bookId = bookRepository.findById(shoppingCartRequest.getBookId())
                .orElseThrow(() -> new RuntimeException("BOOK.IS.NOT.FOUND"));
        Optional<Factor> unpaidFactor  = factorRepository.findByUserAndPayed(userId, Payed.UNPAYED);
        Factor factor =  unpaidFactor .orElseGet(() -> createFactor(userId));
        factorRepository.save(factor);
        ShoppingCart shoppingCart = createShoppingCart(bookId, factor, shoppingCartRequest);
        return createShoppingCartResponse(shoppingCartRepository.save(shoppingCart));

    }

    private ShoppingCartResponse createShoppingCartResponse(ShoppingCart shoppingCart) {
        return ShoppingCartResponse.builder()
                .shoppingCart(shoppingCart.getId())
                .factorId(shoppingCart.getFactor().getId())
                .build();
    }

    private ShoppingCart createShoppingCart(Book bookId, Factor factor, ShoppingCartRequest shoppingCartRequest) {
        return ShoppingCart.builder()
                .book(bookId)
                .factor(factor)
                .count(shoppingCartRequest.getBookCount())
                .build();
    }

    private Factor createFactor(User userId) {
        return Factor.builder()
                .user(userId)
                .payed(Payed.UNPAYED)
                .build();
    }
}
