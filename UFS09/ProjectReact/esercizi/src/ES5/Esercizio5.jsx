import React, { createContext, useContext, useState } from "react"

export const Esercizio5 = () => {

    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const [error, setError] = useState("")

    const onChange = (e, where) => {
        if(where === "user")
            setUsername(e.target.value)

        if(where === "psw")
            setPassword(e.target.value)
    }

    const onSubmit = (e) => {
        e.preventDefault()

        if(username === "user" && password === "psw")
            setError("")
        else
            setError("Errore di login")
    }

    return (
        <div>
            <form onSubmit={onSubmit}>
                {error && (<p style={{color: "red"}}>{error}</p>)}
                <label>Username
                    <input type="text" value={username} onChange={(e) => onChange(e, "user")}/> 
                </label>
                <label>Password
                    <input type="text" value={password} onChange={(e) => onChange(e, "psw")}/>
                </label>
                <button type="submit">Submit</button>
            </form>
        </div>
    )
}