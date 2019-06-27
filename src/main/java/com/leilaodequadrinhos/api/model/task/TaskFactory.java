package com.leilaodequadrinhos.api.model.task;

import com.leilaodequadrinhos.api.model.task.bid.*;
import com.leilaodequadrinhos.api.model.task.auction.*;
import com.leilaodequadrinhos.api.model.task.character.*;
import com.leilaodequadrinhos.api.model.task.auction.status.FindAllAuctionStatus;
import com.leilaodequadrinhos.api.model.task.auction.status.FindAuctionStatusById;
import com.leilaodequadrinhos.api.model.task.notification.NotificationByEmail;
import com.leilaodequadrinhos.api.model.task.product.*;
import com.leilaodequadrinhos.api.model.task.productStatus.FindProductStatusById;
import com.leilaodequadrinhos.api.model.task.productStatus.FindAllProductStatus;
import com.leilaodequadrinhos.api.model.task.user.*;
import com.leilaodequadrinhos.api.model.task.writer.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class TaskFactory {

    private static final Map<String, Task> tasks = new HashMap<>();

    static {
        tasks.put("POST/activate-user", new ActivateUserByID());
        tasks.put("POST/delete-user", new DeleteUserByID());
        tasks.put("GET/find-all-users", new FindAllUsers());
        tasks.put("GET/find-user-by-email", new FindUserByEmail());
        tasks.put("GET/find-user-by-id", new FindUserByID());
        tasks.put("POST/public/insert-user", new InsertNewUser());
        tasks.put("POST/update-user", new UpdateUser());
        tasks.put("POST/login", new UserLogin());
        tasks.put("GET/session", new Session());
        tasks.put("POST/notification-by-email", new NotificationByEmail());

        tasks.put("GET/find-writer-by-id", new FindWriterById());
        tasks.put("GET/find-writers-by-product", new FindWriterByProduct());
        tasks.put("POST/insert-writer", new InsertWriter());
        tasks.put("GET/find-all-writers", new FindAllWriters());
        tasks.put("POST/relate-writer-to-product", new RelateWriterToProduct());

        tasks.put("POST/insert-new-auction", new InsertNewAuction());
        tasks.put("POST/update-auction", new UpdateAuction());
        tasks.put("POST/update-current-auction-price", new UpdateCurrentAuctionPrice());
        tasks.put("GET/find-auction-by-id", new FindAuctionById());
        tasks.put("GET/public/find-all-auctions", new FindAllAuctions());
        tasks.put("POST/delete-auction-by-id", new DeleteAuctionByID());
        tasks.put("GET/find-all-auctions-by-user", new FindAllAuctionsByUser());
        tasks.put("POST/change-auction-status", new ChangeAuctionStatus());

        tasks.put("GET/find-character-by-id", new FindCharacterById());
        tasks.put("GET/find-characters-by-product", new FindCharactersByProduct());
        tasks.put("POST/insert-character", new InsertCharacter());
        tasks.put("GET/find-all-characters", new FindAllCharacters());
        tasks.put("POST/relate-character-to-product", new RelateCharacterToProduct());

        tasks.put("GET/find-all-product-status", new FindAllProductStatus());
        tasks.put("GET/find-product-status-by-id", new FindProductStatusById());

        tasks.put("GET/find-all-auction-status", new FindAllAuctionStatus());
        tasks.put("GET/find-auction-status-by-id", new FindAuctionStatusById());

        tasks.put("POST/change-product-status", new ChangeProductStatus());
        tasks.put("POST/delete-product", new DeleteProductByID());
        tasks.put("GET/find-product-by-id", new FindProductByID());
        tasks.put("GET/find-products-by-user", new FindProductsByUser());
        tasks.put("POST/insert-product", new InsertProduct());
        tasks.put("POST/update-product", new UpdateProduct());

        tasks.put("GET/bid-count", new BidCount());
        tasks.put("GET/find-all-bids", new FindAllBids());
        tasks.put("GET/find-bid-by-auction", new FindBidsByAuction());
        tasks.put("GET/find-bid-by-id", new FindBidByID());
        tasks.put("GET/find-bid-by-user", new FindBidsByUser());
        tasks.put("POST/insert-bid", new InsertBid());
    }

    public static Task getTask(HttpServletRequest request) {
        Task task = tasks.get(request.getMethod() + request.getPathInfo());
        if (task != null)
            return task;
        else
            return new NULLTask();
    }
}