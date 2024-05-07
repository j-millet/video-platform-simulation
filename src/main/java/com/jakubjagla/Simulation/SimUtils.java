/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jakubjagla.Simulation;

import java.io.File;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

/**
 * A collection of utility functions for the project.
 * @author Jake
 */
public class SimUtils {
    /**
     * Returns a random title from a hardcoded list of randomly generated video titles.
     */
    public static String getRandomTitle(){
        //Video titles courtesy of ChatGPT
        String[] clickbaitTitles = {
            "10 SHOCKING Facts You Won't Believe!",
            "SECRET HACK to Instantly Improve Your Life!",
            "UNBOXING the Latest Tech Gadget You NEED!",
            "I Spent 24 Hours in a HAUNTED House!",
            "How to Make $1000 a Day with This SIMPLE Trick!",
            "EPIC Pranks That Went TOO FAR!",
            "TOP 5 Must-Have Apps for 2023!",
            "REVEALING My Biggest Life Secret!",
            "10 Celebrities You Didn't Know Were RELATED!",
            "DIY Home Remodeling GONE WRONG! (You Won't Believe #7)",
            "HIDDEN Camera Captures CRAZY Moments!",
            "EATING Only RED Foods for 24 Hours Challenge!",
            "I Trained Like a SUPERHERO for a Week!",
            "What's Inside the MYSTERIOUS Box?",
            "TRUTH About [Popular Trend] EXPOSED!",
            "Testing VIRAL TikTok Life Hacks!",
            "LIVE Q&A: Ask Me Anything (You Won't Believe My Answers!)",
            "How I Lost 50 Pounds in 2 Weeks (NOT CLICKBAIT!)",
            "EXCLUSIVE Interview with a REAL ALIEN!",
            "Breaking News: [Celebrity] Caught in SHOCKING Scandal!",
            "Craziest Roller Coaster RIDE EVER!",
            "I Bought a $1000 Mystery Box from the DARK WEB!",
            "Ultimate Guide to INSTANT Wealth (Guaranteed Results!)",
            "GHOST CAUGHT on Camera! (100% REAL Footage)",
            "Testing the STRANGEST Beauty Products on the Market!",
            "10 Bizarre Conspiracy Theories That Might Be TRUE!",
            "SHOCKING Before and After Celebrity Transformations!",
            "I Let a RANDOM Number Control My Life for a Day!",
            "MYSTERY Box Opening - You Won't Believe What's Inside!",
            "10 Signs You're a GENIUS (Number 7 Will SHOCK You!)",
            "I Ate ONLY One Color of Food for 24 Hours Challenge!",
            "EXPOSING the TRUTH Behind [Popular Conspiracy]!",
            "I Built a ROBOTIC Version of Myself?! (AI Experiment)",
            "TOP 3 Magic Tricks REVEALED! You Can Do It Too!",
            "TESTING Outrageous As Seen on TV Products!",
            "I Wore a BLINDFOLD for a Day - What Happened Will AMAZE You!",
            "DISCOVERING a Hidden Treasure in My Backyard!",
            "Eating the SPICIEST Foods in the World Challenge!",
            "BECOMING a Professional GAMER in 24 Hours!",
            "I SWAPPED Lives with a Celebrity for a Day!",
            "10 DIY Life Hacks That Will BLOW Your Mind!",
            "LIVE GHOST Hunt in the Abandoned Asylum!",
            "What's In My BAG Challenge - SHOCKING Items Revealed!",
            "I Spent a Night in the WORLD'S Smallest Hotel Room!",
            "REACTING to My OLD Embarrassing Videos!",
            "Unlocking the SECRETS of Ancient Artifacts!",
            "I Traveled to a Parallel Universe - REAL Proof!",
            "Attempting the IMPOSSIBLE Water Bottle Flip!",
            "I Made $1000 in One Hour - Here's How!",
            "Catching a RARE Pokemon in the Wild!",
            "My PET Tried ASMR for the First Time!",
            "I Ate Like a Celebrity for a Week - See the Results!",
            "Behind the Scenes of a VIRAL YouTube Video!",
            "Trying the WEIRDEST Ice Cream Flavors!",
            "10 Times I CHEATED Death - True Stories!",
            "I Lived Without My Phone for a Week - What I Learned!",
            "Exploring a ABANDONED Theme Park at Night!",
            "I Transformed My Room into a JUNGLE! (CRAZY Makeover)",
            "Trying WEIRD Food Combinations - Taste Test Challenge!",
            "HACKING the Universe with Quantum Physics!",
            "I Became a ZOMBIE for a Day - Makeup Tutorial!",
            "10 ANIMALS You Won't Believe Exist!",
            "I Let My Dog Walk Me for a Day - HILARIOUS Results!",
            "Ultimate FASHION Haul - Massive Shopping Spree!",
            "COOKING with Only 3 Ingredients - Can It Taste Good?",
            "I Lived in a Tiny House for a Week - Micro Living Challenge!",
            "MIND-BLOWING Optical Illusions You Can Try at Home!",
            "My CAR Became a DIY Hot Tub! (Waterproof Experiment)",
            "I Spent a Night on a Deserted Island - Survival Challenge!",
            "Testing the WILDEST TikTok Challenges!",
            "I Trained Like an OLYMPIC Athlete for a Month!",
            "10 HIDDEN Meanings in Famous Logos!",
            "I Let Instagram Control My Life for 24 Hours!",
            "Eating ONLY Fast Food for a Week - Extreme Challenge!",
            "BUILDING a Secret Underground Bunker - Doomsday Prep!",
            "Crazy HAIR Dye Experiment - I Dyed My Hair Rainbow!",
            "I Tried the WORST-RATED Restaurant in My City!",
            "EXPLORING Abandoned Places at Night - SCARY Encounter!",
            "I Became a Professional CHEF in One Day!",
            "DIY Giant GUMMY Bear - World's Largest Candy!",
            "24 Hours in a FAKE Fantasy World - Immersive Experience!",
            "My CAT Picks My Outfits for a Week!",
            "Breaking the World Record for the Longest Domino Chain!",
            "I Spent a Day Communicating Only in EMOJIS!",
            "10 Most EXPENSIVE Things I Own - Luxury Revealed!",
            "I Ate Like a SUPERHERO for 7 Days - Marvel vs. DC Diet!"
        };
        return getRandomElementFromArray(clickbaitTitles);
    }
    
    /**
     * Returns a sample video description.
     */
    public static String getVideoDescription(){
        //Video description half courtesy of chatgpt. The other half is a few lines of the Bee Movie script. 
        return    "This serves as an illustrative example of a video description"
                + " that content creators might consider drafting when producing"
                + " their latest YouTube masterpiece. While it's entirely plausible"
                + " that some creators might opt for brevity or explore entirely"
                + " different themes in their video descriptions, this initial template"
                + " provides a solid foundation. Crafting compelling video descriptions "
                + "is an art form, a delicate dance between providing a sneak peek into"
                + " the content and luring the viewer in with a sense of curiosity. As"
                + " video creators, we're faced with the challenge of encapsulating the"
                + " essence of our content in a few lines, inviting the audience to join"
                + " us on a journey of discovery, entertainment, or education. So, whether"
                + " it's an epic adventure, a tutorial on mastering a new skill, or an"
                + " exploration of the unknown, the video description is our virtual"
                + " handshake with the audience, setting the stage for what's to come."
                + " Embracing the power of words, we strive to entice, engage, and build anticipation,"
                + " knowing that a well-crafted description is the gateway to a viewer's experience."
                + " Keep in mind that the magic lies not only in what we say but also in how we say it,"
                + " creating an irresistible allure that beckons viewers to click that play button and"
                + " immerse themselves in the world we've crafted for them. BEES -> According to all"
                + " known laws  of aviation,  there is no way a bee  should be able to fly.  "
                + "Its wings are too small to get  its fat little body off the ground.  The bee,"
                + " of course, flies anyway  because bees don't care  what humans think is impossible."
                + "  Yellow, black. Yellow, black.  Yellow, black. Yellow, black.  Ooh, black and yellow!"
                + "  Let's shake it up a little.  Barry! Breakfast is ready!  Ooming!  Hang on a second."
                + "  Hello?  - Barry?  - Adam?  - Oan you believe this is happening?"
                + "  - I can't. I'll pick you up.  Looking sharp.  Use the stairs."
                + " Your father  paid good money for those.  Sorry. I'm excited.  Here's the graduate."
                + "  We're very proud of you, son.  A perfect report card, all B's.  Very proud."
                + "  Ma! I got a thing going here.  - You got lint on your fuzz.  - Ow! That's me!"
                + "  - Wave to us! We'll be in row 118,000.  - Bye!  Barry, I told you,  stop flying in the house!"
                + "  - Hey, Adam.  - Hey, Barry.  - Is that fuzz gel?  - A little. Special day, graduation."
                + "  Never thought I'd make it.  Three days grade school,  three days high school.  Those were awkward."
                + "  Three days college. I'm glad I took  a day and hitchhiked around the hive."
                + "  You did come back different.  - Hi, Barry.  - Artie, growing a mustache? Looks good."
                + "  - Hear about Frankie?  - Yeah.  - You going to the funeral?  - No, I'm not going."
                + "  Everybody knows,  sting someone, you die.  Don't waste it on a squirrel.  Such a hothead."
                + "  I guess he could have  just gotten out of the way.  I love this incorporating "
                + " an amusement park into our day.  That's why we don't need vacations.  Boy, quite a bit of pomp..."
                + "  under the circumstances.  - Well, Adam, today we are men.  - We are!  - Bee-men.  - Amen!"
                + "  Hallelujah!  Students, faculty, distinguished bees,  please welcome Dean Buzzwell."
                + "  Welcome, New Hive Oity  graduating class of...  ...9:15.  That concludes our ceremonies. "
                + " And begins your career  at Honex Industries!  Will we pick ourjob today?  I heard it's just orientation."
                + "  Heads up! Here we go.  Keep your hands and antennas  inside the tram at all times."
                + "  - Wonder what it'll be like?  - A little scary.  Welcome to Honex,  a division of Honesco"
                + "  and a part of the Hexagon Group.  This is it!  Wow.  Wow.  We know that you, as a bee,"
                + "  have worked your whole life  to get to the point where you  can work for your whole life."
                + "  Honey begins when our valiant Pollen  Jocks bring the nectar to the hive."
                + "   Something something something, I need a big block of text. ";
    }
    
    public static String getRandomThumbnailURI(){
        File dir = new File("assets/thumbnails");
        File[] files = dir.listFiles();
        try{
            return getRandomElementFromArray(files).getAbsolutePath();
        }catch(Exception ex){
            return "/dev/null";
        }
    }
    public static String getRandomProfilePictureURI(){
        File dir = new File("assets/profile_pictures");
        File[] files = dir.listFiles();
        try{
            return getRandomElementFromArray(files).getAbsolutePath();
        }catch(Exception ex){
            return "/dev/null";
        }
    }
    
    public static <T> T getRandomElementFromArray(ArrayList<T> list){
        int idx = ThreadLocalRandom.current().nextInt(list.size());
        return (T) list.get(idx);
    }
    public static <T> T getRandomElementFromArray(T[] list){
        int idx = ThreadLocalRandom.current().nextInt(list.length);
        return (T) list[idx];
    }
    
    public static String getDateStringFromTimestamp(long timestamp){
        Date d = new Date(timestamp);
        LocalDateTime ldt = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return ldt.format(DateTimeFormatter.ofPattern("E, dd/MM/yyyy 'at' hh a"));
    }
}
