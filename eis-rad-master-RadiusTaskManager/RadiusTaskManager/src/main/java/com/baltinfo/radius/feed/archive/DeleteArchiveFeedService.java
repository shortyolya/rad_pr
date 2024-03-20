package com.baltinfo.radius.feed.archive;

import com.baltinfo.radius.db.controller.FeedAdController;
import com.baltinfo.radius.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

public class DeleteArchiveFeedService {
    private static final Logger logger = LoggerFactory.getLogger(DeleteArchiveFeedService.class);

    private final FeedAdController feedAdController;

    public DeleteArchiveFeedService(FeedAdController feedAdController) {
        this.feedAdController = feedAdController;
    }

    public Result<Void, String> deleteArchiveFeed() {
        try {
            Date date = Date.from(ZonedDateTime.now(ZoneOffset.UTC).minusMonths(6).toInstant());
            Result<Void, String> result = feedAdController.deleteArchiveFeedAd(date);
            return result.isSuccess() ? Result.ok() : Result.error("Ошибка БД при удалении архивных фидов: " + result.getError());
        } catch (RuntimeException ex) {
            logger.error("Error deleteArchiveFeed", ex);
            return Result.error("Ошибка при удалении архивных фидов: " + ex.getMessage());
        }
    }
}
