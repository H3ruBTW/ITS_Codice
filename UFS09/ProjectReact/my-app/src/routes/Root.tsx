import { Outlet } from "react-router"

export const Root = () => {
    return (
        <div>
            <h1>Homepage</h1>
            <Outlet />
        </div>
    )
}