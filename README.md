# 🛋️ JetFurniture Ecosystem

### A full-stack e-commerce solution bridging Native Android & Modern Web.

![Kotlin](https://img.shields.io/badge/Kotlin-2.0-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-UI-4285F4?style=for-the-badge&logo=android&logoColor=white)
![React](https://img.shields.io/badge/React-Admin-61DAFB?style=for-the-badge&logo=react&logoColor=black)
![Firebase](https://img.shields.io/badge/Firebase-Realtime-FFCA28?style=for-the-badge&logo=firebase&logoColor=black)

---

## 🚀 Overview

**JetFurniture** is a robust dual-platform system designed to simulate a real-world furniture retail business. It consists of two synchronized applications working in real-time:

1.  **📱 Android User App:** A native shopping experience built with **Jetpack Compose** where users browse, filter, and purchase items.
2.  **💻 Web Admin Dashboard:** A responsive **React.js** panel for business owners to manage inventory, track live orders, and update shipping statuses.

---

## 📸 Screenshots

| Android Customer App | Web Admin Panel |
[8659](https://github.com/user-attachments/assets/8444ff5e-e1fd-43d9-92e5-5e4e4d9626b1)
![8659](https://github.com/user-attachments/assets/616b4646-4d54-4143-b4a2-6ec6918820f8)

![8660](https://github.com/user-attachments/assets/9c6052b7-08a0-4613-9dca-7f6471a83f20)



---

## 🛠️ Tech Stack

### 📱 Android Client (Customer)
* **Language:** Kotlin
* **UI Framework:** Jetpack Compose (Material Design 3)
* **Architecture:** MVVM (Model-View-ViewModel)
* **State Management:** StateFlow & LiveData
* **Navigation:** Compose Navigation

### 💻 Web Client (Admin)
* **Framework:** React.js
* **Styling:** Modern CSS (Glassmorphism & Grid)
* **State:** React Hooks (useState, useEffect)

### 🔥 Backend & Cloud
* **Database:** Firebase Firestore (NoSQL)
* **Sync:** Real-time listeners (`onSnapshot`) for instant updates across devices.

---

## ✨ Key Features

### For the Customer (Android)
* 🛒 **Smart Cart:** Add/remove items with instant price calculation.
* 🔍 **Dynamic Browsing:** Filter furniture by categories (New Arrivals, Best Sellers).
* 📦 **Order Tracking:** Real-time status updates (Pending → Shipped → Delivered).
* 🎨 **Modern UI:** Smooth transitions and animated navigation.

### For the Admin (Web)
* 📊 **Live Order Feed:** Incoming orders appear instantly without refreshing.
* ✏️ **Inventory Control:** CRUD operations (Create, Read, Update, Delete) for products.
* 🚚 **Status Management:** One-click workflow to move orders from "Pending" to "Shipped".

---

## 🔧 Installation

**1. Clone the Repo**
```bash
git clone [https://github.com/smile-web-tech/JetFurnitureApp.git](https://github.com/smile-web-tech/JetFurnitureApp.git)
