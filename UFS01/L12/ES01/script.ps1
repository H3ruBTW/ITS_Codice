$src = "Inp"
$out = "Out"

$oggi = Get-Date -Format "yyyyMMdd_"

Get-ChildItem $src -Filter *.txt | ForEach-Object {
    $nome = $oggi + $_.Name
    Copy-Item $_.FullName -Destination (Join-Path $out $nome)
}