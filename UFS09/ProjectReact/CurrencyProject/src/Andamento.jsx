import React, { useEffect, useState } from "react";
import Chart from 'chart.js/auto';
import { data } from "react-router";

export const Andamento = () => {

    const [valuta, setValuta] = useState()

    const valute = []

    //recupero valute e primo rendering tabella
    useEffect(()=>{
        
    }, [])

    //rendering tabelle dopo cambio input
    useEffect(()=>{

    }, [valuta])

    const onChange = (e) => {
        setValuta(e.target.value)
    }

    const renderTable = () => {
        const labels = []
        const dataset = []

        new Chart(document.getElementById('canvas'), {
            type: 'line',
            data: {
                labels: labels,
                datasets: dataset
            },
            options: {}
        });
    }

    return (
        <>
            <h1>Andamento valuta</h1>
            <label>Valuta<br />
                <select onChange={onChange}>
                    <option value="es1">ciao</option>
                </select>
            </label>
            <canvas id="canvas">
                <p>Rendering non effettuato</p>
            </canvas>
        </>
    )
}