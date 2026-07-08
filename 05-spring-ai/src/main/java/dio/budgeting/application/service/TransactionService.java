package dio.budgeting.application.service;

import dio.budgeting.domain.Category;
import dio.budgeting.domain.Transaction;
import dio.budgeting.domain.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> findAllByCategory(Category category) {
        return transactionRepository.findAllByCategory(category);
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public void loadSampleData() {
        List<Transaction> existing = transactionRepository.findAllByCategory(Category.GROCERIES);
        if (existing.isEmpty()) {
            transactionRepository.save(new Transaction("Supermercado", 15050, Category.GROCERIES));
            transactionRepository.save(new Transaction("Restaurante", 8500, Category.GROCERIES));
            transactionRepository.save(new Transaction("Uber", 2500, Category.AUTO));
            transactionRepository.save(new Transaction("Farmácia", 3990, Category.PHARMA));
            System.out.println("✅ Dados de exemplo carregados!");
        }
    }
}