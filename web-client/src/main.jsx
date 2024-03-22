import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import HomeScreen from './screens/HomeScreen';
import ProductsInCategoryScreen from './screens/ProductsInCategoryScreen';
import 'bootstrap/dist/css/bootstrap.min.css';
import './assets/style.css'
import './assets/bootstrap.custom.css'
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider
} from 'react-router-dom'
import { Provider } from 'react-redux';
import store from './store';
import RegisterScreen from './screens/RegisterScreen';
import LoginScreen from './screens/LoginScreen';
import ProductScreen from './screens/ProductScreen';
import SearchScreen from './screens/SearchScreen';
import CartScreen from './screens/CartScreen';

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/' element={<App />}>
      <Route index path='/' element={<HomeScreen />}></Route>
      <Route index path='/cart' element={<CartScreen />}></Route>
      <Route index path='/search/:keyword' element={<SearchScreen />}></Route>
      <Route index path='/products/:product_id' element={<ProductScreen />}></Route>
      <Route index path='/categories/:category_alias' element={<ProductsInCategoryScreen />}></Route>
      <Route index path='/login' element={<LoginScreen />}></Route>
      <Route index path='/register' element={<RegisterScreen />}></Route>
    </Route>
  )
)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </React.StrictMode>
);

