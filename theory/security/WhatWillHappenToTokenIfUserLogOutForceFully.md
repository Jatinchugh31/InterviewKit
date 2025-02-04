As JWT token are stateLess we can't change the tokne once they are created , 

so we need to blackList the give token so if some one login with the exisitng token user will throw the error . 

we can make a table and store these token as blackList toke, 
and once time is completed we can dump the token