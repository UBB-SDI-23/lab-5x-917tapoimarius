import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import GetAllGames from './components/games/GetAllGames'
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import React from 'react'
import { AppMenu } from './components/AppMenu'
import { Homepage } from './components/Homepage'
import { AddGame } from './components/games/AddGame'
import { DeleteGame } from './components/games/DeleteGame'
import { UpdateGame } from './components/games/UpdateGame'
import GetAllDevelopers from './components/developers/GetAllDevelopers'
import { AddDeveloper } from './components/developers/AddDeveloper'
import { DeleteDeveloper } from './components/developers/DeleteDeveloper'
import { UpdateDeveloper } from './components/developers/UpdateDeveloper'
import GetAllCustomer from './components/customers/GetAllCustomer'
import { AddCustomer } from './components/customers/AddCustomer'
import { DeleteCustomer } from './components/customers/DeleteCustomer'
import { UpdateCustomer } from './components/customers/UpdateCustomer'
import GetAllTransactions from './components/transactions/GetAllTransactions'
import { AddTransaction } from './components/transactions/AddTransaction'
import { DeleteTransaction } from './components/transactions/DeleteTransaction'
import { UpdateTransaction } from './components/transactions/UpdateTransaction'
import { CustomerSpent } from './components/customers/CustomerSpent'

function App() {
  const [count, setCount] = useState(0)

  return (
    <React.Fragment>
      <Router>
        <AppMenu/>
        <Routes>
          <Route path="/" element={<Homepage/>} />
          <Route path="/games" element={<GetAllGames/>}/>
          <Route path="/games/add" element={<AddGame/>}/>
          <Route path="/games/delete/:id" element={<DeleteGame/>}/>
          <Route path="/games/update/:id" element={<UpdateGame/>}/>

          <Route path="/developers" element={<GetAllDevelopers/>}/>
          <Route path="/developers/add" element={<AddDeveloper/>}/>
          <Route path="/developers/delete/:id" element={<DeleteDeveloper/>}/>
          <Route path="/developers/update/:id" element={<UpdateDeveloper/>}/>

          <Route path="/customers" element={<GetAllCustomer/>}/>
          <Route path="/customers/add" element={<AddCustomer/>}/>
          <Route path="/customers/delete/:id" element={<DeleteCustomer/>}/>
          <Route path="/customers/update/:id" element={<UpdateCustomer/>}/>
          <Route path='/customers/customersSpent' element={<CustomerSpent/>}/>

          <Route path="/transactions" element={<GetAllTransactions/>}/>
          <Route path="/transactions/add" element={<AddTransaction/>}/>
          <Route path="/transactions/delete/:id" element={<UpdateTransaction/>}/>
          <Route path="/transactions/update/:id" element={<UpdateDeveloper/>}/>
        </Routes>
      </Router>
    </React.Fragment>
  )
}

export default App
