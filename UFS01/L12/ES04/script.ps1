$name = "Spooler"

$serv = Get-Service -Name $name

if($serv.status -ne 'Running'){
    Start-Service $name
    Add-Content -Path .\log.txt -Value "Il servizio è stato attivato"
} else {
    Add-Content -Path .\log.txt -Value "Il servizio è già attivo"
}