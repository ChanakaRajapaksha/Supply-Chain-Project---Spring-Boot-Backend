# Supply Chain Project | Spring Boot Backend

 <h1>Supply Chain Pro System</h1>

  <h2>Overview</h2>
    <p>The <strong>Supply Chain Pro System</strong> is a microservices-based architecture designed to manage products, inventory, and orders. It includes three main services:</p>
    <ul>
        <li><strong>Product Service</strong>: Handles product information.</li>
        <li><strong>Order Service</strong>: Manages order placement and coordination.</li>
        <li><strong>Inventory Service</strong>: Maintains item stock levels.</li>
    </ul>

  <h2>Key Features</h2>

  <h3>1. Synchronous Communication</h3>
    <ul>
        <li><strong>Order & Inventory</strong>: Before placing an order, the Inventory Service ensures the item quantity is more than zero.</li>
        <li><strong>Order & Product</strong>: The Product Service verifies that the product is available for sale.</li>
    </ul>
    <p>Synchronous HTTP communication between these services ensures the necessary checks are completed before an order is placed.</p>

  <h3>2. Asynchronous Communication with Apache Kafka</h3>
  <p>After an order is committed, an asynchronous event-driven system using <strong>Apache Kafka</strong> sends messages to the Inventory Service, indicating that an order is pending.</p>

  <h3>3. Service Discovery with Netflix Eureka</h3>
    <p>The system uses <strong>Netflix Eureka Server</strong> for service discovery, enabling the scaling of microservice instances efficiently.</p>
    <p>Eureka Server runs on <code>localhost:8761</code>.</p>

   ![Uploading Screenshot 2024-09-28 161132.png…]()

  <h3>4. API Gateway and Security</h3>
    <ul>
        <li><strong>API Gateway</strong> with <strong>Spring Cloud</strong> is used to route requests to different microservices.</li>
        <li><strong>Spring Security</strong> and <strong>Keycloak</strong> are integrated to secure the APIs.</li>
    </ul>

  <h3>5. Service Monitoring</h3>
    <p><strong>Prometheus</strong> and <strong>Grafana</strong> are employed for real-time monitoring and visualization of the system's metrics.</p>

  <h2>Technologies Used</h2>
    <ul>
        <li><strong>Java</strong> & <strong>Spring Boot</strong></li>
        <li><strong>Microservices Architecture</strong></li>
        <li><strong>Spring Cloud Gateway</strong></li>
        <li><strong>Netflix Eureka</strong> for Service Discovery</li>
        <li><strong>Apache Kafka</strong> for Asynchronous Communication</li>
        <li><strong>Prometheus</strong> & <strong>Grafana</strong> for Monitoring</li>
        <li><strong>Spring Security</strong> & <strong>Keycloak</strong> for API Security</li>
        <li><strong>MySQL</strong> for Database</li>
    </ul>

  <h2>How to Run</h2>
    <ol>
        <li><strong>Clone the Repository:</strong>
            <pre><code>git clone https://github.com/ChanakaRajapaksha/Supply-Chain-Project---Spring-Boot-Backend.git</code></pre>
        </li>
        <li><strong>Run Services:</strong></li>
        <ul>
            <li>Start <strong>Eureka Server</strong>: <code>localhost:8761</code></li>
            <li>Launch the microservices (Product, Order, Inventory).</li>
            <li>Set up <strong>Apache Kafka</strong>.</li>
            <li>Monitor services with <strong>Prometheus</strong> and <strong>Grafana</strong>.</li>
        </ul>
    </ol>
