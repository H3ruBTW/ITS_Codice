import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { RouterProvider } from "react-router/dom"
import { createBrowserRouter } from 'react-router'
import { Root } from './Root'

const router = createBrowserRouter([
  {
    path: "/",
    element: <Root />,
    /*children: [
      {
        index: true,
        element: <Home />
      }
    ]*/
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <RouterProvider router={router}/>
  </StrictMode>
)
