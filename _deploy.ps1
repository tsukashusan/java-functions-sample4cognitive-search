$tenantId = "<tenantId>"
$subscriptionId = "<subscriptionId>"
$resourceGroupName = "<resourceGroupName>"
$functionName = "<functionName>"

az login --tenant $tenantId 
az account set --subscription $subscriptionId

$resourceGroupName = "<resourceGroupName>"
$functionName = "<functionName>"


$files = Get-ChildItem -Path .\target\azure-functions\javafuncsample -recurse -Exclude local.settings.json

Compress-Archive -Path $files  -DestinationPath ..\java_functions.zip -Force

az functionapp deployment source config-zip --resource-group $resourceGroupName --name $functionName --src ..\java_functions.zip