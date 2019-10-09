# Shipping Service Project

This project is a simple system that allows customers to place shipping orders for items. This system consists of the following:

1. A service that sits in front of the shipping database and provides CRUD access to the data.
2. An edge service that provides an API that allows customers to place shipping orders, lookup past orders by Customer ID, enforces business logic for calculating shipping price, and that communicates with the CRUD service via a Feign client.
3. A Eureka Service Registry. The CRUD service registers with the Eureka service and the edge service uses the Eureka service to locate the CRUD service.

## Requirements

### CRUD Service

The CRUD microservice provides REST API create, read, update, and delete functionality for Invoice and Invoice Item entries in the backing database.

### Edge Service

* The edge microservice provides an endpoint that allows customers to ship one or more items.
* The edge microservice provides an endpoint that allows customers to look up past orders by Customer ID.
* The edge microservice enforces the following business rules in the service layer:
  * Sales tax is 7.2%. Sales tax applies to both shipping cost and surcharges.
  * Shipping cost of each item is calculated by zipcode:
    * $9.99 for zip codes starting with 0, 1, and 2
    * $14.99 for zip codes starting with 3
    * $19.99 for zip codes starting with 4, 5, and 6
    * $24.99 for zip codes starting with 7, 8, and 9
  * A weight surcharge is assessed based on the cumulative weight total of all items in the order:
    * $8.50 if the total is over 10 pounds
    * $12.50 if the total is over 17 pounds
    * $19.99 if the total is over 25 pounds
    * $50.00 if the total  is over 35 pounds

## Database

```sql
create schema if not exists shipping;
use shipping;

create table if not exists invoice (
	  invoice_id int(11) not null auto_increment primary key,
    customer_id int(11) not null,
    shipto_zip char(5) not null,
    purchase_date date not null,
    total_cost decimal(7,2) not null,
    sales_tax decimal(7,2) not null,
    surcharge decimal(7,2) not null
);

create table if not exists invoice_item (
	  invoice_item_id int(11) not null auto_increment primary key,
    invoice_id int(11) not null,
    item_name varchar(50) not null,
    item_description varchar(255) not null,
    weight int(11) not null,
    ship_cost decimal(7,2) not null    
);

alter table invoice_item add constraint fk_invoice_item_invoice foreign key (invoice_id) references invoice(invoice_id);
```
