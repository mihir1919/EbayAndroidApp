package com.example.myapplication;

import java.util.ArrayList;
import java.util.Date;

public class FetchItemsJSONPOJO {
    public class Condition{
        public ArrayList<String> conditionId;
        public ArrayList<String> conditionDisplayName;
    }

    public class ConvertedCurrentPrice{
        public String currencyId;
        public String __value__;
    }

    public class CurrentPrice{
        public String currencyId;
        public String __value__;
    }

    public class DiscountPriceInfo{
        public ArrayList<OriginalRetailPrice> originalRetailPrice;
        public ArrayList<String> pricingTreatment;
        public ArrayList<String> soldOnEbay;
        public ArrayList<String> soldOffEbay;
    }

    public class findItemsAdvancedResponse{
        public ArrayList<String> ack;
        public ArrayList<String> version;
        public ArrayList<Date> timestamp;
        public ArrayList<SearchResult> searchResult;
        public ArrayList<PaginationOutput> paginationOutput;
        public ArrayList<String> itemSearchURL;
    }

    public class Item{
        public ArrayList<String> itemId;
        public ArrayList<String> title;
        public ArrayList<String> globalId;
        public ArrayList<PrimaryCategory> primaryCategory;
        public ArrayList<String> galleryURL;
        public ArrayList<String> viewItemURL;
        public ArrayList<String> autoPay;
        public ArrayList<String> postalCode;
        public ArrayList<String> location;
        public ArrayList<String> country;
        public ArrayList<ShippingInfo> shippingInfo;
        public ArrayList<SellingStatus> sellingStatus;
        public ArrayList<ListingInfo> listingInfo;
        public ArrayList<String> returnsAccepted;
        public ArrayList<Condition> condition;
        public ArrayList<String> isMultiVariationListing;
        public ArrayList<DiscountPriceInfo> discountPriceInfo;
        public ArrayList<String> topRatedListing;
        public ArrayList<ProductId> productId;
        public ArrayList<String> subtitle;
    }

    public class ListingInfo{
        public ArrayList<String> bestOfferEnabled;
        public ArrayList<String> buyItNowAvailable;
        public ArrayList<Date> startTime;
        public ArrayList<Date> endTime;
        public ArrayList<String> listingType;
        public ArrayList<String> gift;
        public ArrayList<String> watchCount;
    }

    public class OriginalRetailPrice{
        public String currencyId;
        public String __value__;
    }

    public class PaginationOutput{
        public ArrayList<String> pageNumber;
        public ArrayList<String> entriesPerPage;
        public ArrayList<String> totalPages;
        public ArrayList<String> totalEntries;
    }

    public class PrimaryCategory{
        public ArrayList<String> categoryId;
        public ArrayList<String> categoryName;
    }

    public class ProductId{
        public String type;
        public String __value__;
    }

    public class Root{
        public ArrayList<findItemsAdvancedResponse> findItemsAdvancedResponse;
    }

    public class SearchResult{
        public String count;
        public ArrayList<Item> item;
    }

    public class SellingStatus{
        public ArrayList<CurrentPrice> currentPrice;
        public ArrayList<ConvertedCurrentPrice> convertedCurrentPrice;
        public ArrayList<String> sellingState;
        public ArrayList<String> timeLeft;
    }

    public class ShippingInfo{
        public ArrayList<ShippingServiceCost> shippingServiceCost;
        public ArrayList<String> shippingType;
        public ArrayList<String> shipToLocations;
        public ArrayList<String> expeditedShipping;
        public ArrayList<String> oneDayShippingAvailable;
        public ArrayList<String> handlingTime;
    }

    public class ShippingServiceCost{
        public String currencyId;
        public String __value__;
    }

}
