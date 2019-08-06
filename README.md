# test4
# Clone using git clone URL
# Create database fynd in local.
# Go to test folder and fire below cmd.
# mvn clean install
# Run the spring Boot application.


# API: Sequence.
# Run the First API using URL: http://localhost:8080/warehouse/addCapacity
# Sample request {
#                 	"name":"Warehouse1",
#                	"capacity":3
#               } 

# Then add  Items using URL: http://localhost:8080/warehouse/addItem
# Sample request: {
#                  	"warehouseName":"Warehouse1",
#                      "itemName":"Poha2",
#                     "itemColor":"Pink2"
#                 }

#And Remaining API you can run by following WarehouseController.java file.