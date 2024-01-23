package com.kursinis.prif4kursinis.fxControllers;

import com.kursinis.prif4kursinis.fxControllers.tableviewparameters.CustomerTableParameters;
import com.kursinis.prif4kursinis.fxControllers.tableviewparameters.ManagerTableParameters;
import com.kursinis.prif4kursinis.hibernateControllers.CustomHib;
import com.kursinis.prif4kursinis.hibernateControllers.GenericHib;
import com.kursinis.prif4kursinis.hibernateControllers.UserHib;
import com.kursinis.prif4kursinis.hibernateControllers.WarehouseHib;
import com.kursinis.prif4kursinis.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.*;
import java.util.stream.Collectors;

public class MainShopController implements Initializable {

    @FXML
    public ListView<Product> productList;
    @FXML
    public ListView<Product> orderListView;
    @FXML
    public Tab usersTab;
    @FXML
    public Tab warehouseTab;
    @FXML
    public ListView<Warehouse> warehouseList;
    @FXML
    public TextField addressWarehouseField;
    @FXML
    public TextField titleWarehouseField;
    @FXML
    public Tab ordersTab;
    @FXML
    public Tab productsTab;
    @FXML
    public TabPane tabPane;
    @FXML
    public Tab primaryTab;
    @FXML
    public ListView<Product> productListManager;
    @FXML
    public TextField productTitleField;
    @FXML
    public TextField productAuthorField;
    @FXML
    public DatePicker productReleaseDateField;
    @FXML
    public TextField productManufacturerField;
    @FXML
    public TextField productRecommendedAgeField;
    @FXML
    public TextField productPriceField;
    @FXML
    public ComboBox<ProductType> productGenreComboBox;
    @FXML
    public TextField productTopicField;
    @FXML
    public TextArea productDescriptionField;
    @FXML
    public ComboBox<Warehouse> warehouseComboBox;
    @FXML
    public TextField productFictionTypeField;
    @FXML
    public TextField productFictionSettingField;
    @FXML
    public TextField productEduSubjectField;
    @FXML
    public CheckBox productFictionClassicCheckBox;
    @FXML
    public TextField productEduEditionField;
    @FXML
    public ComboBox<String> productEduLevelComboBox;
    @FXML
    public TextField productChildFeatureField;
    @FXML
    public TextField productChildIllustratorField;
    @FXML
    public ComboBox<String> productChildAgeRangeComboBox;
    public TextField commentTitleField;
    public TextArea commentBodyField;
    public ListView<Comment> commentListField;
    public Tab commentTab;

    //------------------User Tab fields-------------------//
    @FXML
    public TableView<CustomerTableParameters> customerTable;
    @FXML
    public TableView<ManagerTableParameters> managerTable;
    @FXML
    public TableColumn<CustomerTableParameters, Integer> idTableCol;
    @FXML
    public TableColumn<CustomerTableParameters, String> loginTableCol;
    @FXML
    public TableColumn<CustomerTableParameters, String> passwordTableCol;
    @FXML
    public TableColumn<CustomerTableParameters, String> addressTableCol;
    @FXML
    public TableColumn<CustomerTableParameters, Void> dummyCol;
    @FXML
    public VBox productFields;
    @FXML
    public VBox fictionFields;
    @FXML
    public VBox eduFields;
    @FXML
    public VBox childFields;
    @FXML
    public ComboBox<Product> productComboBox;
    public ComboBox<Integer> commentRatingField;
    public Tab messageTab;
    public TableView<Message> messageTable;
    public TableColumn<Message, Integer> messageidColumn;
    public TableColumn<Message, LocalDate> messageCreatedColumn;
    public TableColumn<Message, String> messageContenColumn;
    public TableColumn<Message, Integer> messageCarIdColumn;
    public TextArea messageResponseField;
    public Tab statisticsTab;
    public TableView<Cart> cartData;
    public TableColumn<Cart, Integer> buyerColumn;
    public TableColumn<Cart, LocalDate> dateColumn;
    public TableColumn<Cart, Integer> userColumn;
    public TextField userIdField;
    public DatePicker fromField;
    public DatePicker toField;
    public BarChart cartValueByDayChart;
    public TextField CustomerIdField;
    public TextField OrderValueField;
    public TextField OrderCompletedField;
    public TextArea messageInputField;


    //Sito mums reikia, kad susidetume duomenis, kuriuos paduosime TableView
    private ObservableList<CustomerTableParameters> data = FXCollections.observableArrayList();

    private EntityManagerFactory entityManagerFactory;
    private User currentUser;
    private CustomHib customHib;

    @FXML
    private TableView<Cart> orderTable;

    // Assuming you have a TableColumn for order details
    @FXML
    private TableColumn<CustomerOrder, String> orderDetailsCol;

    // List to store orders
    private ObservableList<Cart> orderList = FXCollections.observableArrayList();

    private CustomerOrder customerOrder;

    private GenericHib genericHib;
    private UserHib userHib;
    private WarehouseHib warehouseHib;


    public void setData(EntityManagerFactory entityManagerFactory, User currentUser) {
        this.entityManagerFactory = entityManagerFactory;
        this.currentUser = currentUser;
        this.genericHib = new GenericHib(entityManagerFactory);
        this.userHib = new UserHib(entityManagerFactory);
        this.warehouseHib = new WarehouseHib(entityManagerFactory);
        limitAccess();
        loadData();
    }

    private void loadData() {
        customHib = new CustomHib(entityManagerFactory);
        productList.getItems().clear();
        productList.getItems().addAll(customHib.getAllRecords(Product.class));

    }

    public void setupCommentRatingField() {

        commentRatingField.setItems(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5));
    }

    private void limitAccess() {
        if (currentUser.getClass() == Manager.class) {
            Manager manager = (Manager) currentUser;

            if (!manager.isAdmin()) {
                managerTable.setDisable(true);
            }
        } else if (currentUser.getClass() == Customer.class) {
            Customer customer = (Customer) currentUser;
//            commentDeleteButton.setDisable(true);
//            commentDeleteButton.setDisable(true);
//            CustomerIdField.setDisable(true);
//            OrderValueField.setDisable(true);
//            OrderCompletedField.setDisable(true);
//            messageInputField.setDisable(true);
            tabPane.getTabs().remove(usersTab);
            tabPane.getTabs().remove(warehouseTab);
            tabPane.getTabs().remove(productsTab);
//            tabPane.getTabs().remove(statisticsTab);
        } else {
            JavaFxCustomUtils.generateAlert(
                    Alert.AlertType.WARNING,
                    "Access Error",
                    "Unauthorized Access",
                    "You do not have permission to access this feature." );
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productGenreComboBox.getItems().addAll(ProductType.values());

        fictionFields.setVisible(false);
        eduFields.setVisible(false);
        childFields.setVisible(false);

        customerTable.setEditable(true);
        idTableCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        loginTableCol.setCellValueFactory(new PropertyValueFactory<>("login"));
        passwordTableCol.setCellValueFactory(new PropertyValueFactory<>("password"));
        addressTableCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        //orderDetailsCol.setCellValueFactory(new PropertyValueFactory<>("orderDetails"));
        orderTable.setItems(orderList);
        setupCommentRatingField();
    }

    //----------------------Cart functionality-----------------------------//
    @FXML
    public void createOrder() {
        EntityManager em = null;
         try {
            em = genericHib.getEntityManager();
            em.getTransaction().begin();
            ObservableList<Product> productsInOrderList = orderListView.getItems();
            if(productsInOrderList.isEmpty()){
                JavaFxCustomUtils.generateAlert(Alert.AlertType.ERROR, "Error", "You do know, that you'll still pay for delivery even if we won't send you anything? Try again..", "");
                return;
            }
            Cart cart = new Cart();
            cart.setDateCreated(LocalDate.now());
            cart.setUser(currentUser);

            cart.setItemsInCart(new ArrayList<>());

            for(Product product: productsInOrderList){
                Product managedProduct = em.merge(product);
                managedProduct.setCart(cart);
                cart.getItemsInCart().add(managedProduct);
            }

            em.persist(cart);
            em.getTransaction().commit();
            orderListView.getItems().clear();
        } catch (Exception e){
            if(em != null){
                em.getTransaction().rollback();
            }
            JavaFxCustomUtils.generateAlert(Alert.AlertType.ERROR, "Error", "We actually don't know what happened here, so we'll just leave an error code 1 here, good luck", "");

        }finally {
            if(em != null){
                em.close();
            }
        }
    }

    @FXML
    public void addToCart() {
        Product selectedProduct = productList.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            orderListView.getItems().add(selectedProduct);
        } else {
            JavaFxCustomUtils.generateAlert(Alert.AlertType.ERROR, "Error", "Please select a product to add to the cart.", "");
        }
    }

    @FXML
    public void removeFromCart(){
        Product selectedProduct = orderListView.getSelectionModel().getSelectedItem();

        if(selectedProduct != null){
            orderListView.getItems().remove(selectedProduct);
        }else{
            JavaFxCustomUtils.generateAlert(Alert.AlertType.ERROR, "Error", "Please select a product to remove from the cart", "");
        }
    }

    //----------------------Orders functionality-----------------------------//
    public void getAllCarts() {
        if (currentUser instanceof Customer) {
            List<Cart> customerCarts = genericHib.getCartsForUser(currentUser.getId());
            orderTable.getItems().setAll(customerCarts);
        } else {
            List<Cart> carts = genericHib.getAllRecords(Cart.class);
            orderTable.getItems().setAll(carts);
        }
    }

    public void deleteCart() {
        try {
            Cart selectedCart = orderTable.getSelectionModel().getSelectedItem();

            if (selectedCart == null) {
                showAlert("No Selection", "Please select a cart in the list.");
                return;
            }

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete the selected cart?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                genericHib.delete(Cart.class, selectedCart.getId());

                getAllCarts();
                showAlertConfirm("Deletion Successful", "The cart was successfully deleted.");
            } else {
                getAllCarts();
            }
        } catch (IllegalArgumentException e) {
            showAlert("Error", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred while attempting to delete the cart: " + e.getMessage());
        }
    }

    public void updateCart() {
        try {
            Cart selectedCart = orderTable.getSelectionModel().getSelectedItem();
            if (selectedCart == null) {
                showAlert("No Selection", "No cart selected. Please select a cart in the list.");
                return;
            }

            int newUserId = Integer.parseInt(CustomerIdField.getText());
            double newCartValue = Double.parseDouble(OrderValueField.getText());
            boolean newOrderCompleted = Boolean.parseBoolean(OrderCompletedField.getText().trim().toLowerCase());

            User user = genericHib.getEntityById(User.class, newUserId);

            if (user != null) {
                selectedCart.setUser(user);  // Update the user associated with the cart
            } else {
                showAlert("Error", "No user found with the provided ID.");
                return;
            }

            selectedCart.setCompleted(newOrderCompleted); // Update the order completion status

            genericHib.update(selectedCart);

            getAllCarts();

            showAlertConfirm("Update Successful", "The cart was updated successfully.");

        } catch (NumberFormatException e) {
            showAlert("Invalid Data", "Please enter valid numerical values for user ID and cart value.");
        } catch (Exception e) {
            showAlert("Error", "An error occurred while updating the cart: " + e.getMessage());
        }
    }






    public void loadTabValues() {
        if (productsTab.isSelected()) {
            loadProductListManager();
            List<Warehouse> record = customHib.getAllRecords(Warehouse.class);
            warehouseComboBox.getItems().addAll(customHib.getAllRecords(Warehouse.class));
        } else if (warehouseTab.isSelected()) {
            loadWarehouseList();
        } else if (commentTab.isSelected()) {
            loadCommentList();
            loadProductListManager();
            List<Product> record = genericHib.getAllRecords(Product.class);
            productComboBox.getItems().addAll(record);
        } else if (usersTab.isSelected()) {
            loadUserData();
        } else if(ordersTab.isSelected()){
            getAllCarts();
        }
    }

    private void loadUserData() {
        customerTable.getItems().clear();
        //Kreipiuosi i database ir prasau duomenu
        List<Customer> customerList = customHib.getAllRecords(Customer.class);
        for (Customer c : customerList) {
            CustomerTableParameters customerTableParameters = new CustomerTableParameters();
            customerTableParameters.setId(c.getId());
            customerTableParameters.setLogin(c.getLogin());
            customerTableParameters.setPassword(c.getPassword());
            customerTableParameters.setAddress(c.getAddress());
            //Tureciau pasibaigti su likusiais stulpeliais
            data.add(customerTableParameters);
        }

        customerTable.setItems(data);
    }

    public void enableProductFields() {
        ProductType selectedProductType = productGenreComboBox.getSelectionModel().getSelectedItem();

        // Hide all field containers initially


        if (selectedProductType == ProductType.FICTIONBOOK) {
            fictionFields.setVisible(true);
            eduFields.setVisible(false);
            childFields.setVisible(false);
        } else if (selectedProductType == ProductType.EDUCATIONALBOOK) {
            eduFields.setVisible(true);
            fictionFields.setVisible(false);
            childFields.setVisible(false);
        } else if (selectedProductType == ProductType.CHILDRENSBOOK) {
            childFields.setVisible(true);
            fictionFields.setVisible(false);
            eduFields.setVisible(false);
        }
    }

    //----------------------Product functionality-------------------------------//

    private void loadProductListManager() {
        productListManager.getItems().clear();
        productListManager.getItems().addAll(customHib.getAllRecords(Product.class));
    }

    public void addNewProduct() {
        try {
            // Input validation
            if (!isValidInput()) {
                showError("Invalid input. Please check your input fields.");
                return;
            }

            if (productGenreComboBox.getSelectionModel().getSelectedItem() == ProductType.FICTIONBOOK) {
                Warehouse selectedWarehouse = warehouseComboBox.getSelectionModel().getSelectedItem();
                Warehouse warehouse = customHib.getEntityById(Warehouse.class, selectedWarehouse.getId());
                customHib.create(new FictionBook(productTitleField.getText(),
                        productAuthorField.getText(),
                        Double.parseDouble(productPriceField.getText()),
                        productManufacturerField.getText(),
                        Integer.parseInt(productRecommendedAgeField.getText()),
                        productTopicField.getText(),
                        productDescriptionField.getText(),
                        productReleaseDateField.getValue(),
                        warehouse,
                        productFictionTypeField.getText(),
                        productFictionSettingField.getText(),
                        productFictionClassicCheckBox.isSelected()
                ));
                loadProductListManager();
            } else if (productGenreComboBox.getSelectionModel().getSelectedItem() == ProductType.EDUCATIONALBOOK) {
                Warehouse selectedWarehouse = warehouseComboBox.getSelectionModel().getSelectedItem();
                Warehouse warehouse = customHib.getEntityById(Warehouse.class, selectedWarehouse.getId());
                customHib.create(new EducationalBook(productTitleField.getText(),
                        productAuthorField.getText(),
                        Double.parseDouble(productPriceField.getText()),
                        productManufacturerField.getText(),
                        Integer.parseInt(productRecommendedAgeField.getText()),
                        productTopicField.getText(),
                        productDescriptionField.getText(),
                        productReleaseDateField.getValue(),
                        warehouse,
                        productEduSubjectField.getText(),
                        productEduEditionField.getText(),
                        productEduLevelComboBox.getValue()
                ));
                loadProductListManager();
            } else {
                Warehouse selectedWarehouse = warehouseComboBox.getSelectionModel().getSelectedItem();
                Warehouse warehouse = customHib.getEntityById(Warehouse.class, selectedWarehouse.getId());
                customHib.create(new ChildrensBook(productTitleField.getText(),
                        productAuthorField.getText(),
                        Double.parseDouble(productPriceField.getText()),
                        productManufacturerField.getText(),
                        Integer.parseInt(productRecommendedAgeField.getText()),
                        productTopicField.getText(),
                        productDescriptionField.getText(),
                        productReleaseDateField.getValue(),
                        warehouse,
                        productChildFeatureField.getText(),
                        productChildIllustratorField.getText(),
                        productChildAgeRangeComboBox.getValue()
                ));
                loadProductListManager();
            }


        } catch (NumberFormatException e) {
            showError("Invalid numeric input. Please enter valid numbers.");
        }
        loadProductListManager();
        loadData();
    }

    private boolean isValidInput() {
        return !productTitleField.getText().isEmpty()
                && !productAuthorField.getText().isEmpty()
                && !productPriceField.getText().isEmpty()
                && !productRecommendedAgeField.getText().isEmpty()
                && warehouseComboBox.getValue() != null
                && productGenreComboBox.getValue() != null;
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void updateProduct() {
        Product selectedProduct = productListManager.getSelectionModel().getSelectedItem();

        if (selectedProduct != null) {
            EntityManager em = customHib.getEntityManager();

            try {
                em.getTransaction().begin();

                selectedProduct = em.merge(selectedProduct);

                selectedProduct.setTitle(productTitleField.getText());
                selectedProduct.setAuthor(productAuthorField.getText());

                em.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (em != null) em.close();
            }

            loadProductListManager();
        }
    }


    public void deleteProduct() {

        Product selectedProduct = productListManager.getSelectionModel().getSelectedItem();
        customHib.deleteProduct(selectedProduct.getId());

        loadProductListManager();
    }

    //----------------------Warehouse functionality-----------------------------//

    private void loadWarehouseList() {
        warehouseList.getItems().clear();
        warehouseList.getItems().addAll(customHib.getAllRecords(Warehouse.class));
    }

    public void addNewWarehouse() {
        customHib.create(new Warehouse(titleWarehouseField.getText(), addressWarehouseField.getText()));
        loadWarehouseList();
    }

    public void updateWarehouse() {
        Warehouse selectedWarehouse = warehouseList.getSelectionModel().getSelectedItem();
        Warehouse warehouse = customHib.getEntityById(Warehouse.class, selectedWarehouse.getId());
        warehouse.setTitle(titleWarehouseField.getText());
        warehouse.setAddress(addressWarehouseField.getText());
        customHib.update(warehouse);
        loadWarehouseList();
    }

    public void removeWarehouse() {
        Warehouse selectedWarehouse = warehouseList.getSelectionModel().getSelectedItem();
        Warehouse warehouse = customHib.getEntityById(Warehouse.class, selectedWarehouse.getId());
        customHib.delete(Warehouse.class, selectedWarehouse.getId());
        loadWarehouseList();
    }

    public void loadWarehouseData() {
        Warehouse selectedWarehouse = warehouseList.getSelectionModel().getSelectedItem();
        titleWarehouseField.setText(selectedWarehouse.getTitle());
        addressWarehouseField.setText(selectedWarehouse.getAddress());
    }

    //--------------Comment test section ------------------------//

    @FXML
    private void leaveComment(){
        tabPane.getSelectionModel().select(commentTab);
    }

    private void loadCommentList() {
        commentListField.getItems().clear();
        commentListField.getItems().addAll(customHib.getAllRecords(Comment.class));
    }

    public void createComment() {
        try {
            Product selectedProduct = productComboBox.getSelectionModel().getSelectedItem();
            Integer selectedRating = commentRatingField.getSelectionModel().getSelectedItem();

            if (selectedProduct != null && selectedRating != null) {
                Product product = genericHib.getEntityById(Product.class, selectedProduct.getId());

                Comment newComment = new Comment(
                        product,                // the actual product object
                        product.getTitle(),     // product name or title
                        commentBodyField.getText(), // comment text
                        selectedRating

                );
                genericHib.create(newComment);
                commentBodyField.clear();
                loadCommentList();
            }
        } catch (Exception ignored) {

        }
    }


    public void updateComment() {
        try {

            Comment selectedComment = commentListField.getSelectionModel().getSelectedItem();
            if (selectedComment == null) {

                showAlert("No Selection", "No comment selected. Please select a comment in the list.");
                return;
            }



            //selectedComment.setCommentBody(commentBodyField.getText());
            //selectedComment.setRating(commentRatingField.getValue());
            Integer selectedRating = commentRatingField.getSelectionModel().getSelectedItem();

            if (selectedRating != null) {
                selectedComment.setRating(selectedRating);
            }


            genericHib.update(selectedComment);

            loadCommentList();

            showAlertConfirm("Update Successful", "The comment was updated successfully.");

        } catch (Exception e) {
            // Handle other exceptions. For example, issues related to Hibernate, database, etc.
            showAlert("Error", "An error occurred while updating the comment: " + e.getMessage());
        }
    }

    public void deleteComment() {
        Comment selectedComment = commentListField.getSelectionModel().getSelectedItem();
        //Comment commentFromDb = genericHib.getEntityById(Comment.class, selectedComment.getId());
        customHib.delete(Comment.class, selectedComment.getId());
        loadCommentList();
    }

    public void loadCommentInfo() {
        Comment selectedComment = commentListField.getSelectionModel().getSelectedItem();
        commentTitleField.setText(selectedComment.getCommentTitle());
        commentBodyField.setText(selectedComment.getCommentBody());
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void showAlertConfirm(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }


    //--------------MEssages  test section ------------------------//

    public void sendMessage() {
        try {
            Cart selectedCart = orderTable.getSelectionModel().getSelectedItem();
            if (selectedCart == null) {
                showAlert("No Selection", "No cart selected. Please select a cart in the list.");
                return;
            }

            // Retrieve the message content from messageInputField
            String messageContent = messageInputField.getText();

            if (messageContent != null && !messageContent.trim().isEmpty()) {
                // Create a new Message object
                Message newMessage = new Message(messageContent, selectedCart);

                // Save the new message to the database using your generic Hibernate controller
                genericHib.create(newMessage);

                // Optionally, clear the message input field after sending the message
                messageInputField.clear();

                // If you have any method to refresh or load the messages list, call it here
                // loadMessageList(); // Uncomment or modify according to your application
            } else {
                showAlert("Error", "You must enter a message to send.");
            }
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred while sending your message: " + e.getMessage());
        }
    }

    public void messageResponse() {
        try {
            // Get the selected message from the table
            Message selectedMessage = messageTable.getSelectionModel().getSelectedItem();

            if (selectedMessage == null) {
                showAlert("No Selection", "No message selected. Please select a message in the list.");
                return;
            }

            // Get the cart associated with the selected message
            Cart cart = selectedMessage.getCart();

            // Retrieve the response content from messageResponseField
            String responseContent = messageResponseField.getText();

            if (responseContent != null && !responseContent.trim().isEmpty()) {
                // Create a new message object for the response
                Message responseMessage = new Message(responseContent, cart);
                genericHib.create(responseMessage); // Save the new message

                messageResponseField.clear(); // Clear the text field

                // Optional: Refresh the message list to show the new message
                // loadMessageList(); // Uncomment or adapt this if you have a method to refresh the message list
            } else {
                showAlert("Error", "You must enter a message to send.");
            }
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred while sending your response: " + e.getMessage());
        }
        getAllMessages();
    }

    public void deleteMessage() {
        try {
            // Get the selected message from the table
            Message selectedMessage = messageTable.getSelectionModel().getSelectedItem();

            if (selectedMessage == null) {
                showAlert("No Selection", "No message selected. Please select a message in the list.");
                return;
            }

            // Optional: Confirmation dialog before deletion
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("Are you sure you want to delete the selected message?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Delete the selected message
                genericHib.delete(Message.class, selectedMessage.getId());

                // Optional: Refresh the message list to reflect the deletion
                // loadMessageList(); // Uncomment or adapt this if you have a method to refresh the message list
                showAlertConfirm("Deletion Successful", "The message was successfully deleted.");
            }
        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred while deleting the message: " + e.getMessage());
        }
        getAllMessages();
    }

    public void getAllMessages(){
        List<Message> messages = genericHib.getAllRecords(Message.class);
        messageTable.getItems().setAll(messages);
    }
    //--------------Stats test section ------------------------//

    public void filterProjects() {
        int userId = Integer.parseInt(userIdField.getText());
        LocalDate fromDate = fromField.getValue();
        LocalDate toDate = toField.getValue();

        List<Cart> filteredData = genericHib.filterData(userId, fromDate, toDate);

        cartData.getItems().clear();
        cartData.getItems().addAll(filteredData);


        cartValueByDayChart.getData().clear();


        XYChart.Series<String, Number> series = new XYChart.Series<>();

        cartValueByDayChart.getData().add(series);
    }
}
