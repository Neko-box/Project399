<html>
    <body>
        <h2>SEND A POST</h2>
        <form action="posts" method="post" enctype="multipart/form-data">
            <div>
                <label for="title">Title:</label>
                <input type="text" name="title" id="title" maxlength="100">
            </div>

            <div>
                <label for="text">Text (up to 150 words):</label>
                <textarea name="text" id="text" rows="4" cols="50" maxlength="150"></textarea>
            </div>

            <div>
                <label for="file">Choose Image to Upload:</label>
                <input type="file" name="file" id="file" accept="image/*">
            </div>

            <div>
                <input type="submit" value="Submit">
            </div>
        </form>
    </body>
    <style>
        form {
            margin: 20px;
        }
        label, textarea, input[type="text"], input[type="file"] {
            display: block;
            width: 80%;
            margin: 5px auto;
        }
        input[type="submit"] {
            width: 20%;
            margin: 10px auto;
            display: block;
        }
    </style>
</html>
