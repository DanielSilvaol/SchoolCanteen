let cart = [];

function goToStep(step) {
    document.querySelectorAll('.step').forEach(stepDiv => {
        stepDiv.style.display = 'none';
    });
    document.getElementById(`step${step}`).style.display = 'block';
}

async function fetchCustomers() {
    // Fetch customers from the server
    // This is a mock function. Replace with actual API call.
    return [
        { id: 1, name: 'Customer 1' },
        { id: 2, name: 'Customer 2' },
        // Add more customers
    ];
}

async function fetchProducts() {
    // Fetch products from the server
    // This is a mock function. Replace with actual API call.
    return [
        { id: 1, name: 'Coca', imageUrl: 'https://images.tcdn.com.br/img/img_prod/858764/refrigerante_coca_cola_lata_350ml_c_12_359_1_20201021152315.jpg' },
        { id: 2, name: 'Product 2', imageUrl: 'https://via.placeholder.com/150' },
        // Add more products
    ];
}

function populateCustomers() {
    const customerSelect = document.getElementById('customer');
    fetchCustomers().then(customers => {
        customers.forEach(customer => {
            const option = document.createElement('option');
            option.value = customer.id;
            option.textContent = customer.name;
            customerSelect.appendChild(option);
        });
    });
}

function populateProducts() {
    const productGrid = document.getElementById('productGrid');
    fetchProducts().then(products => {
        products.forEach(product => {
            const img = document.createElement('img');
            img.src = product.imageUrl;
            img.alt = product.name;
            img.title = product.name;
            img.onclick = () => addToCart(product);
            productGrid.appendChild(img);
        });
    });
}

function addToCart(product) {
    const item = cart.find(item => item.product.id === product.id);
    if (item) {
        item.quantity++;
    } else {
        cart.push({ product, quantity: 1 });
    }
    displayCart();
}

function displayCart() {
    const cartTableBody = document.getElementById('cartTable').querySelector('tbody');
    cartTableBody.innerHTML = '';
    cart.forEach((item, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${item.product.name}</td>
            <td>${item.quantity}</td>
            <td><button onclick="removeFromCart(${index})">Remove</button></td>
        `;
        cartTableBody.appendChild(row);
    });
}

function removeFromCart(index) {
    cart.splice(index, 1);
    displayCart();
}

function submitOrder() {
    const customerId = document.getElementById('customer').value;
    const paymentType = document.getElementById('paymentType').value;

    if (!customerId || !paymentType || cart.length === 0) {
        alert('Please complete all steps.');
        return;
    }

    const orderData = {
        customerId,
        paymentType,
        items: cart.map(item => ({
            productId: item.product.id,
            quantity: item.quantity
        }))
    };

    fetch('/school/sales/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(orderData)
    }).then(response => {
        if (response.ok) {
            window.location.href = '/school/sales';
        } else {
            alert('Error submitting order');
        }
    });
}

window.addEventListener('DOMContentLoaded', () => {
    goToStep(1);
    // populateCustomers();
    populateProducts();
});
