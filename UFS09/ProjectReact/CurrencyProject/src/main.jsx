import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { RouterProvider } from "react-router/dom"
import { createBrowserRouter } from 'react-router'
import { Root } from './Root'
import { Home } from './Home'
import { Scambio } from './Scambio'
import './css/index.css'
import { ContextProvider } from './ThemeContext'
import { Andamento } from './Andamento'

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    children: [
      {
        index: true,
        element: <Home />
      },
      {
        path: "/currency",
        element: <Andamento />
      },
      {
        path: "/exchange",
        element: <Scambio />
      },
    ]
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <ContextProvider>
      <RouterProvider router={router}/>
    </ContextProvider>
  </StrictMode>
)
