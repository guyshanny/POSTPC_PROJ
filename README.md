guyshanny

Short explanation
-----------------
In this ex, we had to build a self local chat.
I've built a local chat using a two textViews, two editText, one button
and one ListView.

My app
-------
In my app, you enter your name and your message. After submission,
you can see your message in the MESSAGES SECTION.
Each chatter has it's own random color, so it will be clear who have sent 
the message.

E.g
---
	[RED] Guy: hello, whatsup?
	[GREEN] Moshe: fine! its you first android app?
	[RED] Guy: yeh! it is so cool
	[YELLOW] Yael: Hey Guy! Can you build a cool party app for me?
	[RED] Guy: yes
	[YELLOW] Yael: you're the man!


Orientation handling
--------------------
I've used the 'onSaveInstanceState' and wrapped my objects with
'Parcelable' to handle 'onDestroy' & 'onCreate' while changing orientation.
