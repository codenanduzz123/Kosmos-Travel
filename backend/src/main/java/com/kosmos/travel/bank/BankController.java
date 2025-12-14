package com.kosmos.travel.bank;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bank")
@CrossOrigin(origins = "http://localhost:4200") // remove or tighten for production
public class BankController {

    private final BankValidationService bankValidationService;

    public BankController(BankValidationService bankValidationService) {
        this.bankValidationService = bankValidationService;
    }

    /**
     * POST /api/bank/validate
     * Request: BankRequest
     * Response: BankResponse (200 if valid, 400 if invalid)
     */
    @PostMapping("/validate")
    public ResponseEntity<BankResponse> validate(@RequestBody BankRequest req) {
        BankResponse resp = bankValidationService.validate(req);
        if (resp.isValid()) {
            return ResponseEntity.ok(resp);
        } else {
            return ResponseEntity.badRequest().body(resp);
        }
    }
}
