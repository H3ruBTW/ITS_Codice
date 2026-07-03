import React from "react";
import { Outlet, NavLink } from "react-router";

export const Root = () => {
    return (
        <>
            <header>
                <h1>Currency Monitor</h1>
                <nav>
                    <ul>
                        <li><NavLink to="/">Home</NavLink></li>
                        <li><NavLink to="/currency">Monitor Currency</NavLink></li>
                        <li><NavLink to="/exchange">Currency Exchange Rates</NavLink></li>
                    </ul>
                </nav>
            </header>
            <main>
                <Outlet />
            </main>
            <footer>
                <p>Buongallino Alessandro ©<br />All rights reserved.</p>
            </footer>
        </>
    )
}