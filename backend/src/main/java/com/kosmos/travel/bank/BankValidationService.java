package com.kosmos.travel.bank;

import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
public class BankValidationService {

    private static final Pattern ACCOUNT_DIGITS = Pattern.compile("^\\d{10,16}$");
    private static final Pattern CVV = Pattern.compile("^\\d{3,4}$");

    /**
     * Validate the provided bank/card details according to simple demo criteria.
     * Returns a BankResponse with a simulated token when valid.
     */
    public BankResponse validate(BankRequest req) {
        if (req == null) return new BankResponse(false, "Missing request", null);

        String acct = req.getAccountNumber() == null ? "" : req.getAccountNumber().replaceAll("\\s+","");

        if (!ACCOUNT_DIGITS.matcher(acct).matches()) {
            return new BankResponse(false, "Account number must be 10–16 digits", null);
        }

        if (req.getAccountHolder() == null || req.getAccountHolder().trim().length() < 2) {
            return new BankResponse(false, "Account holder name is invalid", null);
        }

        if (req.getCvv() == null || !CVV.matcher(req.getCvv()).matches()) {
            return new BankResponse(false, "CVV must be 3 or 4 digits", null);
        }

        if (!isExpiryValid(req.getExpiry())) {
            return new BankResponse(false, "Expiry date invalid or expired", null);
        }

        if (req.getAmount() == null || req.getAmount() <= 0) {
            return new BankResponse(false, "Amount must be positive", null);
        }

        // Demo limit: disallow huge test amounts
        if (req.getAmount() > 500000) {
            return new BankResponse(false, "Amount exceeds allowed demo limit", null);
        }

        // All checks pass: generate a simulated bank token
        String token = "bank_tok_" + System.currentTimeMillis();
        return new BankResponse(true, "Validated", token);
    }

    private boolean isExpiryValid(String expiry) {
        if (expiry == null || expiry.trim().isEmpty()) return false;
        try {
            YearMonth ym;
            if (expiry.contains("-")) { // "YYYY-MM"
                ym = YearMonth.parse(expiry, DateTimeFormatter.ofPattern("yyyy-MM"));
            } else if (expiry.contains("/")) { // "MM/YY" or "MM/YYYY"
                String[] parts = expiry.split("/");
                if (parts.length != 2) return false;
                int mm = Integer.parseInt(parts[0]);
                int yy = Integer.parseInt(parts[1]);
                int yyyy = (yy < 100) ? (2000 + yy) : yy;
                ym = YearMonth.of(yyyy, mm);
            } else {
                return false;
            }
            YearMonth now = YearMonth.now();
            return !ym.isBefore(now);
        } catch (Exception ex) {
            return false;
        }
    }
}

