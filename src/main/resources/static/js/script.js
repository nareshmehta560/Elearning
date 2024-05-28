document.getElementById('files').addEventListener('change', function(event) {
    var fileListContainer = document.getElementById('fileList');

    // Get selected files
    var files = event.target.files;

    // Display selected files with delete buttons
    for (var i = 0; i < files.length; i++) {
        var file = files[i];
        var fileName = file.name;

        // Create a div container for each file item
        var fileItemContainer = document.createElement('div');
        fileItemContainer.className = 'file-item-container';

        // Display file name
        var fileNameSpan = document.createElement('span');
        fileNameSpan.textContent = fileName;
        fileItemContainer.appendChild(fileNameSpan);

        // Create delete button for this file
        var deleteButton = document.createElement('button');
        deleteButton.textContent = 'Delete';
        deleteButton.className = 'delete-button';
        deleteButton.addEventListener('click', function(e) {
            // Remove the file item container when delete button is clicked
            e.target.parentNode.remove();
        });
        fileItemContainer.appendChild(deleteButton);

        // Append file item container to file list container
        fileListContainer.appendChild(fileItemContainer);
    }
});

document.getElementById('courseForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent form submission

    // Get form values
    var courseName = document.getElementById('courseName').value;
    var description = document.getElementById('description').value;
    var files = document.getElementById('files').files; // Get array of selected files

    // Perform validation (you can add more validation as needed)

    // Create FormData object to send data
    var formData = new FormData();
    formData.append('courseName', courseName);
    formData.append('description', description);

    // Append each selected file to FormData
    for (var i = 0; i < files.length; i++) {
        formData.append('files[]', files[i]);
    }

    // Simulate AJAX request (replace with actual AJAX call)
    setTimeout(function() {
        // Display success message
        var messageDiv = document.getElementById('message');
        messageDiv.innerHTML = 'Course "' + courseName + '" uploaded successfully!';
        messageDiv.style.display = 'block';

        // Clear form fields (except file input)
        document.getElementById('courseName').value = '';
        document.getElementById('description').value = '';
        document.getElementById('files').value = ''; // Reset file input

        // Clear file list display
        var fileListContainer = document.getElementById('fileList');
        fileListContainer.innerHTML = ''; // Clear file list display

        // Hide message after 3 seconds
        setTimeout(function() {
            messageDiv.style.display = 'none';
        }, 3000);
    }, 1000); // Simulating a delay for demonstration
});
