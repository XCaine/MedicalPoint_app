FOR NOW 20.12.2017
1.
Chrome won't allow http request to same domain                  
as both server and web app are running locally                  
you have to install allow-control-allow-origin chrome extension to chrome
https://chrome.google.com/webstore/detail/allow-control-allow-origi/nlfbmbojpeacfghkpbjhddihlkkiljbi/related?hl=en-US 

actually extension from that link stopped working for my request for some reason xd
i installed other cors extension called "toggle cors"; you can look it up if you dont want to 
manually disable web security in your chrome session

2. 
I highly recommend using brackets (text editor) live preview to use the UI, opera and chrome have
problems with loading files from local domain ( same error as mentioned above )

While you have brackets installed just open file->open folder->select web folder->select live preview(that little icon on top right)
That should open the new chrome window with app running on one of the local ports

ALSO
chrome.exe --user-data-dir="C:/Chrome dev session" --disable-web-security
pasting this command into windows run section should solve the problem

3. both angularjs and bootstrap and added locally cause i sometimes my browser refused to load hem from online sources