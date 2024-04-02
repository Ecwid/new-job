# Test task for TypeScript/Vue.js/NodeJS developer in Lightspeed E-Series Customization Service

## Goal
Write a full stack application for an Ecwid store that has:

- Ecwid storefront on a dedicated web page
- Storefront widget with a settings page
- Export catalog to a CSV file

The application must be wrapped in a Docker container.

### Data
Store data must be accessed using [Ecwid REST API](https://api-docs.ecwid.com/reference/rest-api)

The easiest way is to use a demo store. Here's the access info:
- StoreID: 101560752
- Token: public_eaBDuVmrse1hKZun4qaPF3LewugrnEgq

### Storefront widget
Develop a widget that displays N of the recently updated products in a store.

The widget should display on the cart page in a form of upsell block (buy more products) under the current order total. Store customer should be able to choose between different number of items to show.

Products in this widget should be displayed as a product grid. Clicking on a product should direct a customer to a product details page of that product.

Each product grid item should have a "Buy now" button adding that product to customer's cart. The items that were added using this button should be saved in order extra fields.

### Settings page

The settings page needs to have the following blocks:
- Feature description
- Toggle to turn storefront widget ON/OFF
- A list of store products with checkboxes
- Button to export selected products into a CSV file

The settings page must be developed using the [Ecwid CSS Framework](https://api-docs.ecwid.com/reference/ecwid-css-framework)

### Backend

The backend feature should be built with NodeJS and allow your Settings Page component to generate a CSV file with product information in a store.

## Technologies
- Typescript
- VueJS 3
- HTML/CSS
- NodeJS
- Docker

## Design

User interfaces should look nice and readable. 
