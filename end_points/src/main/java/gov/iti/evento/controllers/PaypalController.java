package gov.iti.evento.controllers;

import gov.iti.evento.services.EventTicketService;
import gov.iti.evento.services.PaypalService;
import gov.iti.evento.services.dtos.UserTicketDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaypalController {

    @Autowired
    PaypalService service;
    @Autowired
    EventTicketService eventTicketService;
    public static final String SUCCESS_URL = "pay/success";
    public static final String CANCEL_URL = "pay/cancel";

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @PostMapping(value = "/pay" , consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> payment(@RequestBody UserTicketDto userTicketDto) {
        Map<String, Object> response = new HashMap<>();
        System.out.println(userTicketDto);
        Float price = eventTicketService.getEventTicketPrice(userTicketDto.getEventId(),userTicketDto.getTicketId()).getPrice();
        System.out.println("price->>>"+price);
        try {
            Payment payment = service.createPayment(Double.valueOf(price*userTicketDto.getQuantity()), "USD", "paypal",
                    "sale", "ticket registration", "http://localhost:8888/" + CANCEL_URL,
                    "http://localhost:8888/" + SUCCESS_URL);
            for(Links link:payment.getLinks()) {
                if(link.getRel().equals("approval_url")) {
                    System.out.println("redirect:"+link.getHref());
                    response.put("status", "success");
                    response.put("redirect_url", link.getHref());
                    return response;
                }
            }

        } catch (PayPalRESTException e) {

            e.printStackTrace();
        }
        return response;
    }

    @GetMapping(value = CANCEL_URL)
    public String cancelPay() {
        return "cancel";
    }

    @PostMapping(value = SUCCESS_URL)
    public Map<String, Object> successPay(HttpServletRequest req) throws PayPalRESTException {
      return service.executePayment(req);
    }

}
