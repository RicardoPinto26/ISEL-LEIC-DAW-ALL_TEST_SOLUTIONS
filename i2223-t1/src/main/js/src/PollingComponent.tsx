import React, {useEffect, useState} from 'react';
import {createRoot} from 'react-dom/client'

type PollingProps = {
    uri: string;
    interval: number;
}

function PollingComponent({uri, interval}: PollingProps) {
    const [content, setContent] = useState<string>('');

    function fetchData() {
        console.log("fetching data from " + uri)
        fetch(uri)
            .then(response => response.text())
            .then(text => setContent(text))
            .catch(error => setContent(`Error: ${error}`))
    }

    useEffect(() => {
        fetchData();
        const intervalId = setInterval(fetchData, interval);

        return () => clearInterval(intervalId);
    }, [uri, interval]);

    return (
        <textarea value={content || "Loading!!!"}
                  readOnly
                  style={{
                      width: '100%',
                      height: '80vh',
                      boxSizing: 'border-box',
                      resize: 'none',
                  }}
        />
    )
}

export function demo() {
    const root = createRoot(document.getElementById("container"))
    root.render(<PollingComponent uri="https://httpbin.org/delay/1" interval={2000}/>)
}