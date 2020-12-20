# Gomoku Plan

<ol>
<li>Read through each class and comment what each class/method does</li>
<li>Take notes on datatype outputs</li>
<li>Make a graphic map to indicate which class inherits to etc..</li>
<li>Once there is a clear understanding of what each section does,
 generate testing</li>
</ol>

### <strong>Requirements and Planned Solution</strong>

<strong>Can set up two players.<strong>
* Create a new class that prompts user how many players will be playing.
<strong> For a human player, collect their name. (A random player's name is randomly generated.) </strong>
<ul>Create method or class that prompts user for name if human, if AI generate random name.</ul>
<strong>For each stone placement, use the player's name to ask questions.</strong><br>
<ul>Use getter/setter to acknowledge player, using their name choice.</ul>
Since the random player doesn't require input, the UI should display stone placement and the results of placement. <br> (Random player placement may fail since they don't know what they're doing.)
<ul>Create a while loop that doesn't break until the random player provides an acceptable move and display it in UI.</ul>
<strong>Re-prompt on failed placement. The game must not proceed until a player has made a valid move.</strong>
<ul>If move/position if available break loop else keep prompting player for input.</ul>
<strong>Display the final result of the game.</strong>
<ul>Using setter/getter display the winner to UI.</ul>
<strong>Give the option to play again.</strong>
<ul>Create a method/class leveraging a while loop so the method keeps looping through the game until user exits.</ul>

________________________________________________________________________
