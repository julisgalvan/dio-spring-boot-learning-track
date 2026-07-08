package dio.budgeting.application.controller;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.application.service.TransactionService;
import dio.budgeting.domain.TransactionId;
import dio.budgeting.application.dto.TransactionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    // GET /api/transactions - lista TODAS
    @GetMapping
    public ResponseEntity<List<Transaction>> getAll() {
        return ResponseEntity.ok(transactionService.findAll());
    }

    // GET /api/transactions/category/{category} - lista por categoria
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Transaction>> getByCategory(@PathVariable String category) {
        try {
            Category cat = Category.valueOf(category.toUpperCase());
            return ResponseEntity.ok(transactionService.findAllByCategory(cat));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // POST /api/transactions - cria uma nova transação
    @PostMapping
    public ResponseEntity<Transaction> create(@RequestBody TransactionRequest request) {
        // Cria o ID manualmente
        TransactionId id = new TransactionId();
        Transaction transaction = new Transaction(
                id,
                request.getDescription(),
                request.getAmount(),
                request.getCategory()
        );
        Transaction saved = transactionService.save(transaction);
        return ResponseEntity.ok(saved);
    }

    // POST /api/transactions/load-sample - carrega dados de exemplo
    @PostMapping("/load-sample")
    public ResponseEntity<String> loadSample() {
        transactionService.loadSampleData();
        return ResponseEntity.ok("Dados carregados!");
    }
}