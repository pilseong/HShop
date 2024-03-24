import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import UserListScreen from './screens/UserListScreen';
import CustomerListScreen from './screens/CustomerListScreen';
import CategoryListScreen from './screens/CategoryListScreen';
import BrandListScreen from './screens/BrandListScreen';
import ProductListScreen from './screens/ProductListScreen';
import CreateProductScreen from './screens/CreateProductScreen'
import CreateUserScreen from './screens/CreateUserScreen';
import EditUserScreen from './screens/EditUserScreen';
import HomeScreen from './screens/HomeScreen';

import { Provider } from 'react-redux';
import store from './store';
// import 'bootstrap/dist/css/bootstrap.min.css';
// import './assets/style.css'
import './index.css'
import {
  createBrowserRouter,
  createRoutesFromElements,
  Route,
  RouterProvider
} from 'react-router-dom'
import LoginScreen from './screens/LoginScreen';
import PrivateRoute from './components/PrivateRoute';
import SettingsListScreen from './screens/SettingsListScreen';
import '@fontsource/roboto/300.css';
import '@fontsource/roboto/400.css';
import '@fontsource/roboto/500.css';
import '@fontsource/roboto/700.css';
import { ThemeProvider } from '@mui/material';
import { theme } from './theme'


const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path='/' element={<App />}>
      <Route path='/login' element={<LoginScreen />}></Route>
      <Route path='' element={<PrivateRoute />}>
        <Route index path='/' element={<HomeScreen />}></Route>
        <Route path='/users' element={<UserListScreen />}></Route>
        <Route path='/users/new' element={<CreateUserScreen />}></Route>
        <Route path='/users/:userId' element={<EditUserScreen />}></Route>
        <Route path='/categories' element={<CategoryListScreen />}></Route>
        <Route path='/brands' element={<BrandListScreen />}></Route>
        <Route path='/products' element={<ProductListScreen />}></Route>
        <Route path='/products/new' element={<CreateProductScreen />}></Route>
        <Route path='/customers' element={<CustomerListScreen />}></Route>
        <Route path='/settings' element={<SettingsListScreen />}></Route>
      </Route>
    </Route>
  )
)

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <RouterProvider router={router}></RouterProvider>
      </ThemeProvider>
    </Provider>
  </React.StrictMode>
);

