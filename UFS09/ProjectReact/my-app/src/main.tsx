import { StrictMode } from 'react';
import ReactDOM from 'react-dom/client'
import { createBrowserRouter, RouterProvider } from "react-router"

import { Root } from "./routes/Root.tsx";

const root = document.getElementById("root")

if (!root) {
    throw new Error('Elemento root non trovato')
}

const router = createBrowserRouter([
    {
        path: "/",
        element: <Root />,
        children: [
            {
                index: true,
                element: <p>Ciao</p>
            }
        ]
    }
])

ReactDOM.createRoot(root).render(
    <StrictMode>
        <RouterProvider router={router} />
    </StrictMode>
)