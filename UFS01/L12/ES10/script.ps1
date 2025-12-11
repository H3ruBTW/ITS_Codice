$path = "backup\$(Get-Date -Format "yyyy-MM-dd")"

New-Item -Path $path -ItemType Directory

Copy-Item .\src -Destination $path -Recurse